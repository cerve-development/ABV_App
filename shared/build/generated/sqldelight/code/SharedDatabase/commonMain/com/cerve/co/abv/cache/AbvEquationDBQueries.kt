package com.cerve.co.abv.cache

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class AbvEquationDBQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAbvEquationEntity(mapper: (
    name: String,
    equation: String,
    updatedAt: Long,
  ) -> T): Query<T> = Query(209_443_698, arrayOf("AbvEquationEntity"), driver, "AbvEquationDB.sq",
      "selectAbvEquationEntity", "SELECT * FROM AbvEquationEntity") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!
    )
  }

  public fun selectAbvEquationEntity(): Query<AbvEquationEntity> = selectAbvEquationEntity { name,
      equation, updatedAt ->
    AbvEquationEntity(
      name,
      equation,
      updatedAt
    )
  }

  public fun <T : Any> selectAbvEquationByNameEntity(name: String, mapper: (
    name: String,
    equation: String,
    updatedAt: Long,
  ) -> T): Query<T> = SelectAbvEquationByNameEntityQuery(name) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!
    )
  }

  public fun selectAbvEquationByNameEntity(name: String): Query<AbvEquationEntity> =
      selectAbvEquationByNameEntity(name) { name_, equation, updatedAt ->
    AbvEquationEntity(
      name_,
      equation,
      updatedAt
    )
  }

  public fun upsertAbvEquationEntity(
    name: String,
    equation: String,
    updatedAt: Long,
  ) {
    driver.execute(-842_497_121, """
        |INSERT OR REPLACE INTO
        |AbvEquationEntity
        |VALUES (?,?,?)
        """.trimMargin(), 3) {
          bindString(0, name)
          bindString(1, equation)
          bindLong(2, updatedAt)
        }
    notifyQueries(-842_497_121) { emit ->
      emit("AbvEquationEntity")
    }
  }

  private inner class SelectAbvEquationByNameEntityQuery<out T : Any>(
    public val name: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("AbvEquationEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("AbvEquationEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-201_036_204, """
    |SELECT *
    |FROM AbvEquationEntity
    |WHERE name = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, name)
    }

    override fun toString(): String = "AbvEquationDB.sq:selectAbvEquationByNameEntity"
  }
}
