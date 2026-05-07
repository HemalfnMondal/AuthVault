package com.authvault.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AccountDao_Impl implements AccountDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AccountEntity> __insertionAdapterOfAccountEntity;

  private final EntityDeletionOrUpdateAdapter<AccountEntity> __deletionAdapterOfAccountEntity;

  private final EntityDeletionOrUpdateAdapter<AccountEntity> __updateAdapterOfAccountEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePosition;

  public AccountDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccountEntity = new EntityInsertionAdapter<AccountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `accounts` (`id`,`issuer`,`accountName`,`secretKey`,`algorithm`,`digits`,`period`,`type`,`counter`,`position`,`createdAt`,`iconSlug`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccountEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getIssuer());
        statement.bindString(3, entity.getAccountName());
        statement.bindString(4, entity.getSecretKey());
        statement.bindString(5, entity.getAlgorithm());
        statement.bindLong(6, entity.getDigits());
        statement.bindLong(7, entity.getPeriod());
        statement.bindString(8, entity.getType());
        statement.bindLong(9, entity.getCounter());
        statement.bindLong(10, entity.getPosition());
        statement.bindLong(11, entity.getCreatedAt());
        if (entity.getIconSlug() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getIconSlug());
        }
      }
    };
    this.__deletionAdapterOfAccountEntity = new EntityDeletionOrUpdateAdapter<AccountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `accounts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccountEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAccountEntity = new EntityDeletionOrUpdateAdapter<AccountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `accounts` SET `id` = ?,`issuer` = ?,`accountName` = ?,`secretKey` = ?,`algorithm` = ?,`digits` = ?,`period` = ?,`type` = ?,`counter` = ?,`position` = ?,`createdAt` = ?,`iconSlug` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccountEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getIssuer());
        statement.bindString(3, entity.getAccountName());
        statement.bindString(4, entity.getSecretKey());
        statement.bindString(5, entity.getAlgorithm());
        statement.bindLong(6, entity.getDigits());
        statement.bindLong(7, entity.getPeriod());
        statement.bindString(8, entity.getType());
        statement.bindLong(9, entity.getCounter());
        statement.bindLong(10, entity.getPosition());
        statement.bindLong(11, entity.getCreatedAt());
        if (entity.getIconSlug() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getIconSlug());
        }
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM accounts";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePosition = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE accounts SET position = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AccountEntity account, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAccountEntity.insertAndReturnId(account);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<AccountEntity> accounts,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfAccountEntity.insertAndReturnIdsList(accounts);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final AccountEntity account, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAccountEntity.handle(account);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final AccountEntity account, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAccountEntity.handle(account);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePosition(final int id, final int position,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePosition.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, position);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdatePosition.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AccountEntity>> observeAccounts() {
    final String _sql = "SELECT * FROM accounts ORDER BY position ASC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"accounts"}, new Callable<List<AccountEntity>>() {
      @Override
      @NonNull
      public List<AccountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final int _cursorIndexOfSecretKey = CursorUtil.getColumnIndexOrThrow(_cursor, "secretKey");
          final int _cursorIndexOfAlgorithm = CursorUtil.getColumnIndexOrThrow(_cursor, "algorithm");
          final int _cursorIndexOfDigits = CursorUtil.getColumnIndexOrThrow(_cursor, "digits");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "counter");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIconSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "iconSlug");
          final List<AccountEntity> _result = new ArrayList<AccountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccountEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIssuer;
            _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpSecretKey;
            _tmpSecretKey = _cursor.getString(_cursorIndexOfSecretKey);
            final String _tmpAlgorithm;
            _tmpAlgorithm = _cursor.getString(_cursorIndexOfAlgorithm);
            final int _tmpDigits;
            _tmpDigits = _cursor.getInt(_cursorIndexOfDigits);
            final int _tmpPeriod;
            _tmpPeriod = _cursor.getInt(_cursorIndexOfPeriod);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpCounter;
            _tmpCounter = _cursor.getLong(_cursorIndexOfCounter);
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpIconSlug;
            if (_cursor.isNull(_cursorIndexOfIconSlug)) {
              _tmpIconSlug = null;
            } else {
              _tmpIconSlug = _cursor.getString(_cursorIndexOfIconSlug);
            }
            _item = new AccountEntity(_tmpId,_tmpIssuer,_tmpAccountName,_tmpSecretKey,_tmpAlgorithm,_tmpDigits,_tmpPeriod,_tmpType,_tmpCounter,_tmpPosition,_tmpCreatedAt,_tmpIconSlug);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAccountById(final int id,
      final Continuation<? super AccountEntity> $completion) {
    final String _sql = "SELECT * FROM accounts WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AccountEntity>() {
      @Override
      @Nullable
      public AccountEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final int _cursorIndexOfSecretKey = CursorUtil.getColumnIndexOrThrow(_cursor, "secretKey");
          final int _cursorIndexOfAlgorithm = CursorUtil.getColumnIndexOrThrow(_cursor, "algorithm");
          final int _cursorIndexOfDigits = CursorUtil.getColumnIndexOrThrow(_cursor, "digits");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "counter");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIconSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "iconSlug");
          final AccountEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIssuer;
            _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpSecretKey;
            _tmpSecretKey = _cursor.getString(_cursorIndexOfSecretKey);
            final String _tmpAlgorithm;
            _tmpAlgorithm = _cursor.getString(_cursorIndexOfAlgorithm);
            final int _tmpDigits;
            _tmpDigits = _cursor.getInt(_cursorIndexOfDigits);
            final int _tmpPeriod;
            _tmpPeriod = _cursor.getInt(_cursorIndexOfPeriod);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpCounter;
            _tmpCounter = _cursor.getLong(_cursorIndexOfCounter);
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpIconSlug;
            if (_cursor.isNull(_cursorIndexOfIconSlug)) {
              _tmpIconSlug = null;
            } else {
              _tmpIconSlug = _cursor.getString(_cursorIndexOfIconSlug);
            }
            _result = new AccountEntity(_tmpId,_tmpIssuer,_tmpAccountName,_tmpSecretKey,_tmpAlgorithm,_tmpDigits,_tmpPeriod,_tmpType,_tmpCounter,_tmpPosition,_tmpCreatedAt,_tmpIconSlug);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAccounts(final Continuation<? super List<AccountEntity>> $completion) {
    final String _sql = "SELECT * FROM accounts ORDER BY position ASC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AccountEntity>>() {
      @Override
      @NonNull
      public List<AccountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final int _cursorIndexOfSecretKey = CursorUtil.getColumnIndexOrThrow(_cursor, "secretKey");
          final int _cursorIndexOfAlgorithm = CursorUtil.getColumnIndexOrThrow(_cursor, "algorithm");
          final int _cursorIndexOfDigits = CursorUtil.getColumnIndexOrThrow(_cursor, "digits");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "counter");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIconSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "iconSlug");
          final List<AccountEntity> _result = new ArrayList<AccountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccountEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIssuer;
            _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpSecretKey;
            _tmpSecretKey = _cursor.getString(_cursorIndexOfSecretKey);
            final String _tmpAlgorithm;
            _tmpAlgorithm = _cursor.getString(_cursorIndexOfAlgorithm);
            final int _tmpDigits;
            _tmpDigits = _cursor.getInt(_cursorIndexOfDigits);
            final int _tmpPeriod;
            _tmpPeriod = _cursor.getInt(_cursorIndexOfPeriod);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpCounter;
            _tmpCounter = _cursor.getLong(_cursorIndexOfCounter);
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpIconSlug;
            if (_cursor.isNull(_cursorIndexOfIconSlug)) {
              _tmpIconSlug = null;
            } else {
              _tmpIconSlug = _cursor.getString(_cursorIndexOfIconSlug);
            }
            _item = new AccountEntity(_tmpId,_tmpIssuer,_tmpAccountName,_tmpSecretKey,_tmpAlgorithm,_tmpDigits,_tmpPeriod,_tmpType,_tmpCounter,_tmpPosition,_tmpCreatedAt,_tmpIconSlug);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object existsBySecretKey(final String secretKey,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM accounts WHERE secretKey = ? LIMIT 1)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, secretKey);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
