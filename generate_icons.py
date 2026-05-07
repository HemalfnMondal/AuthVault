#!/usr/bin/env python3
import importlib
import subprocess
import sys
from io import BytesIO
from pathlib import Path
import tempfile


def ensure_pip() -> None:
    try:
        import pip  # noqa: F401
        return
    except ImportError:
        pass

    try:
        import ensurepip
        ensurepip.bootstrap(upgrade=True)
    except Exception as exc:
        raise RuntimeError(
            "pip is not available in this Python environment and could not be bootstrapped with ensurepip"
        ) from exc


def ensure_package(module_name: str, pip_name: str) -> bool:
    try:
        importlib.import_module(module_name)
        return True
    except ImportError:
        try:
            ensure_pip()
            subprocess.check_call([sys.executable, "-m", "pip", "install", pip_name])
            importlib.import_module(module_name)
            return True
        except Exception:
            return False


HAS_CAIROSVG = ensure_package("cairosvg", "cairosvg")
ensure_package("PIL", "Pillow")

if HAS_CAIROSVG:
    import cairosvg  # noqa: E402
from PIL import Image, ImageDraw  # noqa: E402

SVG_ICON = """<svg viewBox=\"0 0 24 24\" xmlns=\"http://www.w3.org/2000/svg\">
  <rect width=\"24\" height=\"24\" fill=\"#FFFFFF\"/>
  <path fill=\"#00BCD4\" d=\"M12 0a1.667 1.667 0 0 0-1.666 1.666A1.667 1.667 0 0 0 12 3.334a1.667 1.667 0 0 0 1.666-1.668A1.667 1.667 0 0 0 12 0ZM9.506 1.145A11.572 11.572 0 0 0 .442 12.317a.716.716 0 0 0 1.075.618.702.702 0 0 0 .358-.6 10.136 10.136 0 0 1 5.06-8.666c.843-.486 1.75-.848 2.696-1.074a2.55 2.55 0 0 1-.125-1.452Zm8.015 1.26a.712.712 0 0 0-.695.713.704.704 0 0 0 .34.61 10.133 10.133 0 0 1 4.545 11.587c.314.046.618.15.894.309.148.084.29.183.42.293a11.573 11.573 0 0 0-5.15-13.43.715.715 0 0 0-.354-.082Zm-5.519 3.791a6.247 6.247 0 1 0 .002 12.494 6.247 6.247 0 0 0-.002-12.494Zm0 1.43a4.819 4.819 0 0 1 3.41 8.222 4.817 4.817 0 1 1-3.41-8.222Zm-.01 2.295c-1.412.014-1.896 1.887-.668 2.584l-.435 2.207a.237.237 0 0 0 .234.281h1.772a.236.236 0 0 0 .234-.281l-.438-2.207c.43-.247.694-.706.692-1.202a1.38 1.38 0 0 0-1.39-1.382zm-9.324 6.242a1.667 1.667 0 0 0-1.666 1.666 1.667 1.667 0 0 0 1.666 1.668 1.667 1.667 0 0 0 1.666-1.668 1.667 1.667 0 0 0-1.666-1.666zm18.664 0a1.667 1.667 0 0 0-1.666 1.666 1.667 1.667 0 0 0 1.666 1.668 1.667 1.667 0 0 0 1.666-1.668 1.667 1.667 0 0 0-1.666-1.666zM4.655 19.427c-.195.244-.432.45-.702.608a2.584 2.584 0 0 1-.468.207 11.576 11.576 0 0 0 14.208 2.273.713.713 0 0 0 0-1.238.704.704 0 0 0-.703-.012 10.134 10.134 0 0 1-10.052-.05 10.17 10.17 0 0 1-2.283-1.788z\"/>
</svg>
"""

SIZES = {
    "mdpi": 48,
    "hdpi": 72,
    "xhdpi": 96,
    "xxhdpi": 144,
    "xxxhdpi": 192,
}

PADDING_RATIO = 0.12


def render_svg(size: int) -> Image.Image:
    if HAS_CAIROSVG:
        png_data = cairosvg.svg2png(bytestring=SVG_ICON.encode("utf-8"), output_width=size, output_height=size)
        image = Image.open(BytesIO(png_data)).convert("RGBA")
        if image.size != (size, size):
            image = image.resize((size, size), Image.Resampling.LANCZOS)
        return image

    with tempfile.NamedTemporaryFile("w", suffix=".svg", delete=False) as svg_file:
        svg_file.write(SVG_ICON)
        svg_path = Path(svg_file.name)
    with tempfile.NamedTemporaryFile(suffix=".png", delete=False) as png_file:
        png_path = Path(png_file.name)

    subprocess.check_call([
        "convert",
        "-background",
        "white",
        str(svg_path),
        "-resize",
        f"{size}x{size}",
        str(png_path),
    ])
    image = Image.open(png_path).convert("RGBA")
    try:
        svg_path.unlink(missing_ok=True)
        png_path.unlink(missing_ok=True)
    except Exception:
        pass
    return image


def apply_padding(image: Image.Image, size: int) -> Image.Image:
    pad = int(round(size * PADDING_RATIO))
    inner = max(1, size - (pad * 2))
    resized = image.resize((inner, inner), Image.Resampling.LANCZOS)
    canvas = Image.new("RGBA", (size, size), (255, 255, 255, 255))
    canvas.paste(resized, (pad, pad), resized)
    return canvas


def make_round(image: Image.Image) -> Image.Image:
    size = image.size[0]
    mask = Image.new("L", (size, size), 0)
    draw = ImageDraw.Draw(mask)
    draw.ellipse((0, 0, size - 1, size - 1), fill=255)
    rounded = Image.new("RGBA", (size, size), (255, 255, 255, 0))
    rounded.paste(image, (0, 0), mask)
    return rounded


def main() -> None:
    root = Path(__file__).resolve().parent / "app" / "src" / "main" / "res"
    for density, size in SIZES.items():
        mipmap_dir = root / f"mipmap-{density}"
        mipmap_dir.mkdir(parents=True, exist_ok=True)

        standard = apply_padding(render_svg(size), size)
        standard.save(mipmap_dir / "ic_launcher.png")

        round_icon = make_round(standard)
        round_icon.save(mipmap_dir / "ic_launcher_round.png")

    print("Generated launcher icons for mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi")


if __name__ == "__main__":
    main()
