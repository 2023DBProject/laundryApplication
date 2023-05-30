package com.example.login;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Profit_Settlement {
    private final int cumulative_profit;
    private final int sales;
    private final int net_profit;
    private final int month;
    private final int average_daily_sales;
    private final int average_weekend_sales;
    private final int expenditure;
    private final int average_weekday_sales;

    public Profit_Settlement() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Profit_Settlement(int cumulative_profit, int sales, int net_profit,
                             int month, int average_daily_sales, int average_weekend_sales, int expenditure, int average_weekday_sales) {
        this.cumulative_profit = cumulative_profit;
        this.sales = sales;
        this.net_profit = net_profit;
        this.month = month;
        this.average_daily_sales = average_daily_sales;
        this.average_weekend_sales = average_weekend_sales;
        this.expenditure = expenditure;
        this.average_weekday_sales = average_weekday_sales;
    }

    public int getCumulative_profit() {
        return cumulative_profit;
    }

    public int getSales() {
        return sales;
    }

    public int getNet_profit() {
        return net_profit;
    }

    public int getMonth() {
        return month;
    }

    public int getAverage_daily_sales() {
        return average_daily_sales;
    }

    public int getAverage_weekend_sales() {
        return average_weekend_sales;
    }

    public int getExpenditure() {
        return expenditure;
    }

    public int getAverage_weekday_sales() {
        return average_weekday_sales;
    }
}