package db.changelog

databaseChangeLog {

  changeSet(id: 'Create student table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: "student")
      }
    }
    createTable(tableName: 'student') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }
      column(name: 'name', type: 'varchar(20)') {}
      column(name: 'last_name', type: 'varchar(30)') {}
      column(name: 'email', type: 'varchar(50)') {}
      column(name: 'date_of_birth', type: 'date') {}
      column(name: 'age', type: 'integer') {}
      column(name: 'sex', type: 'varchar(1)') {}
    }
    createSequence(sequenceName: 'SEQ_student', startValue: '1')
  }
}