from pathlib import Path
from PIL import Image, ImageDraw


ROOT = Path(__file__).resolve().parents[1] / "app" / "src" / "main" / "res"
SIZES = {
    "mdpi": 48,
    "hdpi": 72,
    "xhdpi": 96,
    "xxhdpi": 144,
    "xxxhdpi": 192,
}

WHITE = (255, 255, 255, 255)
CYAN = (0, 188, 212, 255)
BLUE = (21, 101, 192, 255)


def draw_launcher(size: int) -> Image.Image:
    scale = size / 108.0
    image = Image.new("RGBA", (size, size), WHITE)
    draw = ImageDraw.Draw(image)

    def s(value: float) -> int:
        return max(1, int(round(value * scale)))

    cx = cy = size / 2
    circle_r = s(36)
    draw.ellipse((cx - circle_r, cy - circle_r, cx + circle_r, cy + circle_r), fill=CYAN)

    shield = [
        (54, 28),
        (69, 35),
        (67, 55),
        (54, 77),
        (41, 55),
        (39, 35),
    ]
    shield_pts = [(x * scale, y * scale) for x, y in shield]
    draw.polygon(shield_pts, fill=WHITE)

    body_left = s(46)
    body_top = s(53)
    body_right = s(62)
    body_bottom = s(71)
    draw.rounded_rectangle((body_left, body_top, body_right, body_bottom), radius=s(2), fill=BLUE)

    draw.pieslice((s(48), s(47), s(60), s(59)), start=180, end=360, fill=WHITE)
    draw.rectangle((s(51.5), s(52), s(56.5), s(55)), fill=WHITE)

    keyhole_r = s(1.4)
    keyhole_x = s(54)
    keyhole_y = s(58.5)
    draw.ellipse((keyhole_x - keyhole_r, keyhole_y - keyhole_r, keyhole_x + keyhole_r, keyhole_y + keyhole_r), fill=WHITE)

    return image


def main() -> None:
    for bucket, size in SIZES.items():
        out_dir = ROOT / f"mipmap-{bucket}"
        out_dir.mkdir(parents=True, exist_ok=True)
        image = draw_launcher(size)
        image.save(out_dir / "ic_launcher.png")
        image.save(out_dir / "ic_launcher_round.png")


if __name__ == "__main__":
    main()