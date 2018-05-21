package com.halim.adam.cashmaster;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.halim.adam.cashmaster.Objects.BudgetRatio;
import com.halim.adam.cashmaster.Objects.BudgetTransfer;
import com.halim.adam.cashmaster.Objects.Category;
import com.halim.adam.cashmaster.Objects.Income;
import com.halim.adam.cashmaster.Objects.Budget;
import com.halim.adam.cashmaster.Objects.Spending;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cash_master.db";
    private static final int DATABASE_VERSION = 3;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE category (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.execute();
        sql = "CREATE TABLE income (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, amount NUMERIC, date TEXT DEFAULT (date('now')));";
        stmt = db.compileStatement(sql);
        stmt.execute();
        sql = "CREATE TABLE spending (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT DEFAULT (date('now')), amount NUMERIC, categoryId INTEGER, ratioId INTEGER, " +
                "FOREIGN KEY(categoryId) REFERENCES category(id) ON DELETE SET NULL, FOREIGN KEY(ratioId) REFERENCES budget_ratio(id) ON DELETE SET NULL);";
        stmt = db.compileStatement(sql);
        stmt.execute();
        sql = "CREATE TABLE budget (id INTEGER PRIMARY KEY AUTOINCREMENT, ratioId INTEGER, incomeId INTEGER, amount NUMERIC, FOREIGN KEY(ratioId) REFERENCES budget_ratio(id) " +
                "ON DELETE SET NULL, FOREIGN KEY (incomeId) REFERENCES income(id) ON DELETE CASCADE)";
        stmt = db.compileStatement(sql);
        stmt.execute();
        sql = "CREATE TABLE budget_ratio (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, ratio NUMERIC);";
        stmt = db.compileStatement(sql);
        stmt.execute();
        sql = "CREATE TABLE budget_transfer (id INTEGER PRIMARY KEY AUTOINCREMENT, jarFromId INTEGER, jarToId INTEGER, amount NUMERIC, FOREIGN KEY (jarFromId) REFERENCES budget(id)" +
                " ON DELETE SET NULL, FOREIGN KEY (jarToId) REFERENCES budget(id) ON DELETE SET NULL)";
        stmt = db.compileStatement(sql);
        stmt.execute();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
    INSERT TO TABLES
     */

    public void InsertCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO category (name) VALUES ('" + name + "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertIncome(String name, float amount, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO income (name, amount, date) VALUES ('" + name + "', '" + amount + "', '" + DATE_FORMAT.format(date) +  "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertIncome(String name, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO income (name, amount) VALUES ('" + name + "', '" + amount + "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertBudget(int ratioId, int incomeId, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO budget (ratioId, incomeId, amount) VALUES ('" + ratioId + "', '" + incomeId + "', '" + amount + "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertSpending(String name, float amount, int categoryId, int ratioId, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO spending (name, date, amount, categoryId, ratioId) VALUES ('" + name + "', '" + DATE_FORMAT.format(date) + "', '" + amount + "', '" + categoryId + "', '" + ratioId +  "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertSpending(String name, float amount, int categoryId, int ratioId){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO spending (name, amount, categoryId, ratioId) VALUES ('" + name + "', '" + amount + "', '" + categoryId + "', '" + ratioId +  "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertBudgetRatio(String name, float ratio){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO budget_ratio (name, ratio) VALUES ('" + name + "', '" + ratio + "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }
    public void InsertBudgetTransfer(String name, int jarFromId, int jarToId, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO budget_transfer (name, jarFromId, jarToId, amount) VALUES ('" + name + "', '" + jarFromId + "', '" + jarToId + "', '" + amount + "');";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.executeInsert();
        db.close();
    }

    /*
    SELECT ALL
     */

    public ArrayList<Category> GetCategoryList(){
        ArrayList<Category> categoryList = new ArrayList<>();
        Category category;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM category;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++){
                category = new Category();

                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));

                categoryList.add(category);

                cursor.moveToNext();
            }

            db.close();
            return categoryList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<Income> GetIncomeList() throws ParseException {
        ArrayList<Income> incomeList = new ArrayList<Income>();
        Income income;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM income;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++){
                income = new Income();

                income.setId(cursor.getInt(0));
                income.setName(cursor.getString(1));
                income.setAmount(cursor.getFloat(2));
                income.setDate(DATE_FORMAT.parse(cursor.getString(3)));

                incomeList.add(income);

                cursor.moveToNext();
            }

            db.close();
            return incomeList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<Spending> GetSpendingList() throws ParseException {
        ArrayList<Spending> spendingList = new ArrayList<Spending>();
        Spending spending;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM spending;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++) {
                spending = new Spending();

                spending.setId(cursor.getInt(0));
                spending.setName(cursor.getString(1));
                spending.setDate(DATE_FORMAT.parse(cursor.getString(2)));
                spending.setAmount(cursor.getFloat(3));
                spending.setCategoryId(cursor.getInt(4));
                spending.setRatioId(cursor.getInt(5));

                spendingList.add(spending);

                cursor.moveToNext();
            }

            db.close();
            return spendingList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<Budget> GetBudgetList(){
        ArrayList<Budget> budgetList = new ArrayList<Budget>();
        Budget budget;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++) {
                budget = new Budget();

                budget.setId(cursor.getInt(0));
                budget.setRatioId(cursor.getInt(1));
                budget.setIncomeId(cursor.getInt(2));
                budget.setAmount(cursor.getFloat(3));

                budgetList.add(budget);

                cursor.moveToNext();
            }

            db.close();
            return budgetList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<BudgetRatio> GetBudgetRatioList(){
        ArrayList<BudgetRatio> budgetRatioList = new ArrayList<>();
        BudgetRatio budgetRatio;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget_ratio;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++) {
                budgetRatio = new BudgetRatio();

                budgetRatio.setId(cursor.getInt(0));
                budgetRatio.setName(cursor.getString(1));
                budgetRatio.setRatio(cursor.getFloat(2));

                budgetRatioList.add(budgetRatio);

                cursor.moveToNext();
            }

            db.close();
            return budgetRatioList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<BudgetTransfer> GetBudgetTransferList(){
        ArrayList<BudgetTransfer> budgetTransferList = new ArrayList<BudgetTransfer>();
        BudgetTransfer budgetTransfer;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget_transfer;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++) {
                budgetTransfer = new BudgetTransfer();

                budgetTransfer.setId(cursor.getInt(0));
                budgetTransfer.setJarFromId(cursor.getInt(1));
                budgetTransfer.setJarToId(cursor.getInt(2));
                budgetTransfer.setAmount(cursor.getFloat(3));

                budgetTransferList.add(budgetTransfer);

                cursor.moveToNext();
            }

            db.close();
            return budgetTransferList;
        }
        else{
            db.close();
            return null;
        }
    }

    /*
    SELECT FROM TABLES WITH ID
     */

    public Category GetCategory(int id){
        Category category = new Category();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM category WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));

            db.close();
            return category;
        }
        else{
            db.close();
            return null;
        }
    }
    public Income GetIncome(int id) throws ParseException {
        Income income = new Income();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM income WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            income.setId(cursor.getInt(0));
            income.setName(cursor.getString(1));
            income.setAmount(cursor.getFloat(2));
            income.setDate(DATE_FORMAT.parse(cursor.getString(3)));

            db.close();
            return income;
        }
        else{
            db.close();
            return null;
        }
    }
    public Spending GetSpending(int id) throws ParseException {
        Spending spending = new Spending();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM spending WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            spending.setId(cursor.getInt(0));
            spending.setName(cursor.getString(1));
            spending.setDate(DATE_FORMAT.parse(cursor.getString(2)));
            spending.setAmount(cursor.getFloat(3));
            spending.setCategoryId(cursor.getInt(4));
            spending.setRatioId(cursor.getInt(5));

            db.close();
            return spending;
        }
        else{
            db.close();
            return null;
        }
    }
    public Budget GetBudget(int id){
        Budget budget = new Budget();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            budget.setId(cursor.getInt(0));
            budget.setRatioId(cursor.getInt(1));
            budget.setIncomeId(cursor.getInt(2));
            budget.setAmount(cursor.getFloat(3));

            db.close();
            return budget;
        }
        else{
            db.close();
            return null;
        }
    }
    public BudgetRatio GetBudgetRatio(int id){
        BudgetRatio budgetRatio = new BudgetRatio();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget_ratio WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            budgetRatio.setId(cursor.getInt(0));
            budgetRatio.setName(cursor.getString(1));
            budgetRatio.setRatio(cursor.getFloat(2));

            db.close();
            return budgetRatio;
        }
        else{
            db.close();
            return null;
        }
    }
    public BudgetTransfer GetBudgetTransfer(int id){
        BudgetTransfer budgetTransfer = new BudgetTransfer();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget_ratio WHERE id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            budgetTransfer.setId(cursor.getInt(0));
            budgetTransfer.setJarFromId(cursor.getInt(1));
            budgetTransfer.setJarToId(cursor.getInt(2));
            budgetTransfer.setAmount(cursor.getFloat(3));

            db.close();
            return budgetTransfer;
        }
        else{
            db.close();
            return null;
        }
    }

    /*
    SELECT BUDGET USING RATIO_ID
     */

    public ArrayList<Budget> GetBudgetFromRatio(int ratioId){
        ArrayList<Budget> budgetList = new ArrayList<>();
        Budget budget;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM budget WHERE ratioId = '" + ratioId + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            for(int c = 0; c < cursor.getCount(); c++) {
                budget = new Budget();

                budget.setId(cursor.getInt(0));
                budget.setRatioId(cursor.getInt(1));
                budget.setIncomeId(cursor.getInt(2));
                budget.setAmount(cursor.getFloat(3));

                budgetList.add(budget);

                cursor.moveToNext();
            }

            db.close();
            return budgetList;
        }
        else{
            db.close();
            return null;
        }
    }

    /*
    SELECT FROM A DATE
     */

    public ArrayList<Spending> GetSpendingFromDate(Date date) throws ParseException {
        ArrayList<Spending> spendingArrayList = new ArrayList<Spending>();
        Spending spending;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM spending WHERE date > '" + DATE_FORMAT.format(date) + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            for (int c = 0; c < cursor.getCount(); c++) {
                spending = new Spending();

                spending.setId(cursor.getInt(0));
                spending.setName(cursor.getString(1));
                spending.setDate(DATE_FORMAT.parse(cursor.getString(2)));
                spending.setAmount(cursor.getFloat(3));
                spending.setCategoryId(cursor.getInt(4));
                spending.setRatioId(cursor.getInt(5));

                spendingArrayList.add(spending);

                cursor.moveToNext();
            }
            db.close();
            return spendingArrayList;
        } else {
            db.close();
            return null;
        }
    }
    public ArrayList<Spending> GetSpendingFromDate(Date date, int daysAgo) throws ParseException {
        ArrayList<Spending> spendingArrayList = new ArrayList<Spending>();
        Spending spending;

        // get -x days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -daysAgo);
        Date dateDaysAgo = cal.getTime();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM spending WHERE date > '" + DATE_FORMAT.format(dateDaysAgo) + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            for (int c = 0; c < cursor.getCount(); c++) {
                spending = new Spending();

                spending.setId(cursor.getInt(0));
                spending.setName(cursor.getString(1));
                spending.setDate(DATE_FORMAT.parse(cursor.getString(2)));
                spending.setAmount(cursor.getFloat(3));
                spending.setCategoryId(cursor.getInt(4));
                spending.setRatioId(cursor.getInt(5));

                spendingArrayList.add(spending);

                cursor.moveToNext();
            }
            db.close();
            return spendingArrayList;
        } else {
            db.close();
            return null;
        }
    }
    public ArrayList<Income> GetIncomeFromDate(Date date) throws ParseException {
        ArrayList<Income> incomeArrayList = new ArrayList<>();
        Income income;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM income WHERE date > '" + DATE_FORMAT.format(date) + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            for(int c = 0; c < cursor.getCount(); c++) {
                income = new Income();

                income.setId(cursor.getInt(0));
                income.setName(cursor.getString(1));
                income.setAmount(cursor.getFloat(2));
                income.setDate(DATE_FORMAT.parse(cursor.getString(3)));

                incomeArrayList.add(income);

                cursor.moveToNext();
            }
            db.close();
            return incomeArrayList;
        }
        else{
            db.close();
            return null;
        }
    }
    public ArrayList<Income> GetIncomeFromDate(Date date, int daysAgo) throws ParseException {
        ArrayList<Income> incomeArrayList = new ArrayList<Income>();
        Income income;

        // get -x days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -daysAgo);
        Date dateDaysAgo = cal.getTime();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM income WHERE date > '" + DATE_FORMAT.format(dateDaysAgo) + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            for(int c = 0; c < cursor.getCount(); c++) {
                income = new Income();

                income.setId(cursor.getInt(0));
                income.setName(cursor.getString(1));
                income.setAmount(cursor.getFloat(2));
                income.setDate(DATE_FORMAT.parse(cursor.getString(3)));

                incomeArrayList.add(income);

                cursor.moveToNext();
            }
            db.close();
            return incomeArrayList;
        }
        else{
            db.close();
            return null;
        }
    }

    public Income GetLatestIncome() throws ParseException {
        Income income = new Income();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM income ORDER BY id DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            income.setId(cursor.getInt(0));
            income.setName(cursor.getString(1));
            income.setAmount(cursor.getFloat(2));
            income.setDate(DATE_FORMAT.parse(cursor.getString(3)));

            db.close();
            return income;
        }
        else{
            db.close();
            return null;
        }
    }

    /*
    EDIT ROWS
     */

    public void UpdateBudgetRatio(BudgetRatio budgetRatio){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE budget_ratio SET name = '" + budgetRatio.getName() + "', ratio = '" + budgetRatio.getRatio() + "' WHERE id = '" + budgetRatio.getId() + "';";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.execute();
        db.close();
    }

    public void UpdateCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE category SET name = '" + category.getName() + "' WHERE id = '" + category.getId() + "';";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.execute();
        db.close();
    }

    public void UpdateIncome(Income income){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.execute();
        db.close();
    }

    public void UpdateSpending(Spending spending){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.execute();
        db.close();
    }
}
