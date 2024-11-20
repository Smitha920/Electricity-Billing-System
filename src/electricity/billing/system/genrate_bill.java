package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class genrate_bill extends JFrame implements ActionListener {
    Choice SearchMonthCho;
    String meter;
    JTextArea area;
    JButton bill;
    genrate_bill(String meter){
        this.meter = meter;
        setSize(500,700);
        setLocation(500,30);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JLabel heading = new JLabel("Genrate Bill");
        JLabel meter_no = new JLabel(meter);
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
        area = new JTextArea(50,15);
        area.setText("\n \n \t ------------------------ click on the ------------------\n \t ----------------------------Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC,15));
        JScrollPane pane = new JScrollPane(area);
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        add(pane);
        panel.add(heading);
        panel.add(meter_no);
        panel.add(SearchMonthCho);
        add(panel,"North");
        add(bill,"South");
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            database c = new database();
            String smonth = SearchMonthCho.getSelectedItem();
            area.setText("\n power Limited \nElectricity Bill For Month Of "+smonth+" ,2024\n\n\n");
            ResultSet resultSet = c.statement.executeQuery("select *from new_customer where meter_no = '"+meter+"'");
            if (resultSet.next()) {
                area.append("\n   Name            : " + resultSet.getString("name"));
                area.append("\n   Meter Number    : " + resultSet.getString("meter_no"));
                area.append("\n   Address         : " + resultSet.getString("address"));
                area.append("\n   City            : " + resultSet.getString("city"));
                area.append("\n   State           : " + resultSet.getString("state"));
                area.append("\n   Email           : " + resultSet.getString("email"));
                area.append("\n   Phone Number    : " + resultSet.getString("phone_no"));
            }
            resultSet = c.statement.executeQuery("select * from meter_info where meter_number ='" + meter + "'");
            if (resultSet.next()) {
                area.append("\n   Meter Location    : " + resultSet.getString("meter_location"));
                area.append("\n   Meter Type        : " + resultSet.getString("meter_type"));
                area.append("\n   Phase Code        : " + resultSet.getString("phase_code"));
                area.append("\n   Bill Type         : " + resultSet.getString("bill_type"));
                area.append("\n   Days              : " + resultSet.getString("Days"));
            }
            resultSet = c.statement.executeQuery("select * from tax");
            if (resultSet.next()) {
                area.append("\n   Cost Per Unit       : " + resultSet.getString("cost_per_unit"));
                area.append("\n   Meter Rent          : " + resultSet.getString("meter_rent"));
                area.append("\n   Service Charge      : " + resultSet.getString("service_charge"));
                area.append("\n   Service Tax         : " + resultSet.getString("service_tax"));
                area.append("\n   Swacch Bharat       : " + resultSet.getString("swacch_bharat"));
                area.append("\n   Fixed Tax           : " + resultSet.getString("fixed_tax"));
            }
            resultSet = c.statement.executeQuery("select * from bill where meter_no = '" +meter+ "' and month = '" + SearchMonthCho.getSelectedItem() + "'");
            if (resultSet.next()) {
                area.append("\n   Current Month       : " + resultSet.getString("month"));
                area.append("\n   Units Consumed      : " + resultSet.getString("unit"));
                area.append("\n   Total Charges       : " + resultSet.getString("total_bill"));
                area.append("\n   Total Payable       : " + resultSet.getString("total_bill"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new genrate_bill("");
    }
}
