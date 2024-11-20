package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho,SearchMonthCho ;
    JTable table;
    JButton search,print,close;
      deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,245));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);
        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);
        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180,20,150,20);
        add(searchMeterCho);
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while(resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JLabel searchMonth = new JLabel("Search ByMonth");
        searchMonth.setBounds(400,20,100,20);
        add(searchMonth);
          SearchMonthCho = new Choice();
          SearchMonthCho.add("January");
          SearchMonthCho.add("February");
          SearchMonthCho.add("March");
          SearchMonthCho.add("April");
          SearchMonthCho.add("May");
          SearchMonthCho.add("June");
          SearchMonthCho.add("July");
          SearchMonthCho.add("August");
          SearchMonthCho.add("September");
          SearchMonthCho.add("October");
          SearchMonthCho.add("November");
          SearchMonthCho.add("December");
        SearchMonthCho.setBounds(520,20,150,20);
        add(SearchMonthCho);
        table = new JTable();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
        }catch (Exception e){
            e.printStackTrace();
        }
        table = new JTable();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);
        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        close= new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String query_search = "select * from bill where meter_no = '"+searchMeterCho.getSelectedItem()+"'and month = '"+SearchMonthCho.getSelectedItem()+"'";
            try{
                database c =new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print)
        {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
