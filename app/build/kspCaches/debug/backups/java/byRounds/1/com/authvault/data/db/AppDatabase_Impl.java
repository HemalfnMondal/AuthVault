package com.authvault.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AccountDao _accountDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `accounts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `issuer` TEXT NOT NULL, `accountName` TEXT NOT NULL, `secretKey` TEXT NOT NULL, `algorithm` TEXT NOT NULL, `digits` INTEGER NOT NULL, `period` INTEGER NOT NULL, `type` TEXT NOT NULL, `counter` INTEGER NOT NULL, `position` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `iconSlug` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6a8b1708b6a08fdac681b3017823ab1f')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `accounts`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAccounts = new HashMap<String, TableInfo.Column>(12);
        _columnsAccounts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("issuer", new TableInfo.Column("issuer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("accountName", new TableInfo.Column("accountName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("secretKey", new TableInfo.Column("secretKey", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("algorithm", new TableInfo.Column("algorithm", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("digits", new TableInfo.Column("digits", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("period", new TableInfo.Column("period", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("counter", new TableInfo.Column("counter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("iconSlug", new TableInfo.Column("iconSlug", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccounts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccounts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccounts = new TableInfo("accounts", _columnsAccounts, _foreignKeysAccounts, _indicesAccounts);
        final TableInfo _existingAccounts = TableInfo.read(db, "accounts");
        if (!_infoAccounts.equals(_existingAccounts)) {
          return new RoomOpenHelper.ValidationResult(false, "accounts(com.authvault.data.db.AccountEntity).\n"
                  + " Expected:\n" + _infoAccounts + "\n"
                  + " Found:\n" + _existingAccounts);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6a8b1708b6a08fdac681b3017823ab1f", "7601264d7bc59f5c238cc86f79b89e21");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "accounts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `accounts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AccountDao.class, AccountDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AccountDao accountDao() {
    if (_accountDao != null) {
      return _accountDao;
    } else {
      synchronized(this) {
        if(_accountDao == null) {
          _accountDao = new AccountDao_Impl(this);
        }
        return _accountDao;
      }
    }
  }
}
