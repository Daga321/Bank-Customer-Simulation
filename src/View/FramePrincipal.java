package View;

import Controller.Controller;
import Model.Module;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FramePrincipal extends JFrame implements Actioner{


    private JPanel contentPane;
    private JTextField txtTurnoActualModulo1;
    private JTextField txtTurnoActualModulo2;
    private JTextField txtTurnoActualModulo3;
    private JTextField txtCedulaGenerarTurno;
    private JPanel panelModulo3_GenerarTurno;
    private JPanel panelRetiros;
    private JLabel lblNewLabel_2_1;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_4;
    private JPanel panelGenerarTurno;
    private JLabel lblNewLabel_5;
    private JButton btnGenerarTurno;
    private JLabel lblNewLabel_7;
    private JButton btnEstadisticas;
    private JLabel lblNewLabel_8;
    private JComboBox cmbTramite;
    private JPanel panelModulo1_2;
    private JPanel panelPrestamos;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_2;
    private JPanel panelAsesorias;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_3;
    private JPanel panelnformacion;
    private JLabel lblNewLabel_4_1;
    private PanelPerson panelPerson;
    private RunnableRepaintPerson runnableRepaintPerson;
    private Thread threadRepaint;
    private RunnableUpdateTurnos runnableUpdateTurnos;
    private Thread threadTurnos;
    private ArrayList<TurnWithPerson> turnWithPeople;
    private JScrollPane spModule1;
    private JTextArea taDataModule1;
    private JTextArea taQueueModule1;
    private JScrollPane spModule2;
    private JTextArea taDataModule2;
    private JTextArea taQueueModule2;
    private JScrollPane spModule3;
    private JTextArea taDataModule3;
    private JTextArea taQueueModule3;

    public FramePrincipal()  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 1357, 716);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null);;
        setVisible(true);
    }

    public void start() {
        inicializar();
        estilos();
        agregar();
    }

    public void assignController(Controller controller) {
        runnableRepaintPerson.assignController(controller);
        threadRepaint.start();

        runnableUpdateTurnos.assignController(controller);
        threadTurnos.start();

        btnGenerarTurno.setActionCommand(Actioner.CAPTURARDATOS);
        btnGenerarTurno.addActionListener(controller);

        btnEstadisticas.setActionCommand(Actioner.ESTADISTICASVENTANA);
        btnEstadisticas.addActionListener(controller);
    }


    public void inicializar() {
        panelModulo3_GenerarTurno = new JPanel();
        panelModulo3_GenerarTurno.setLayout(null);
        panelModulo3_GenerarTurno.setBounds(1025, 80, 332, 600);
        panelModulo1_2 = new JPanel();
        panelModulo1_2.setLayout(null);
        panelModulo1_2.setBounds(0, 80, 325, 600);
        panelModulo1_2.setBackground(getForeground());
        panelRetiros = new JPanel();
        panelRetiros.setLayout(null);
        panelRetiros.setBounds(0, 0, 332, 300);
        panelPrestamos = new JPanel();
        panelPrestamos.setLayout(null);
        panelPrestamos.setBounds(0, 0, 325, 300);
        panelAsesorias = new JPanel();
        panelAsesorias.setLayout(null);
        panelAsesorias.setBounds(0, 300, 325, 300);
        panelnformacion = new JPanel();
        panelnformacion.setBounds(0, 0, 1357, 80);
        panelPerson = new PanelPerson();
        panelPerson.setBounds(325, 80, 700, 600);
        
        lblNewLabel_4 = new JLabel("M\u00F3dulo 3");
        lblNewLabel_4.setBounds(75, 20, 150, 30);
        lblNewLabel_2_1 = new JLabel("Retiros y consignaciones");
        lblNewLabel_2_1.setBounds(75, 60, 150, 20);

        txtTurnoActualModulo3 = new JTextField();
        txtTurnoActualModulo3.setBounds(75, 95, 150, 20);
        panelGenerarTurno = new JPanel();
        panelGenerarTurno.setLayout(null);
        panelGenerarTurno.setBounds(0, 300, 332, 300);
        lblNewLabel_5 = new JLabel("Generar turno");
        lblNewLabel_6 = new JLabel("Ingrese c\u00E9dula");

        txtCedulaGenerarTurno = new JTextField();

        btnGenerarTurno = new JButton("Generar");
        lblNewLabel_7 = new JLabel("Estad\u00EDsticas");

        btnEstadisticas = new JButton("Ver Estadisticas");

        lblNewLabel_8 = new JLabel("Tr\u00E1mite");
        cmbTramite = new JComboBox();
        cmbTramite.setModel(new DefaultComboBoxModel(Module.values()));

        lblNewLabel = new JLabel("M\u00F3dulo 1");
        lblNewLabel.setBounds(75, 20, 150, 30);

        lblNewLabel_2 = new JLabel("Cr\u00E9ditos y pr\u00E9stamos");
        lblNewLabel_2.setBounds(75, 60, 150, 20);

        txtTurnoActualModulo1 = new JTextField();
        txtTurnoActualModulo1.setBounds(75, 95, 150, 20);

        lblNewLabel_1 = new JLabel("M\u00F3dulo 2");
        lblNewLabel_1.setBounds(75, 20, 150, 30);

        lblNewLabel_3 = new JLabel("Asesor\u00EDas");
        lblNewLabel_3.setBounds(115, 60, 150, 20);

        txtTurnoActualModulo2 = new JTextField();
        txtTurnoActualModulo2.setBounds(75, 95, 150, 20);

        lblNewLabel_4_1 = new JLabel("Simulaci\u00F3n turnos en un banco");

        runnableRepaintPerson = new RunnableRepaintPerson(panelPerson);
        threadRepaint = new Thread(runnableRepaintPerson);

        runnableUpdateTurnos = new RunnableUpdateTurnos(this);
        threadTurnos = new Thread(runnableUpdateTurnos);
        
        taDataModule1 = new JTextArea();
        taDataModule1.setEditable(false);
        taDataModule1.setBounds(15, 125, 271, 50);
        
        taQueueModule1 = new JTextArea();
        taQueueModule1.setEditable(false);
        spModule1 = new JScrollPane(taQueueModule1);
        spModule1.setViewportView(taQueueModule1);
        spModule1.setBounds(15, 185, 271, 95);
        
        taDataModule2 = new JTextArea();
        taDataModule2.setEditable(false);
        taDataModule2.setBounds(15, 125, 271, 50);
        
        taQueueModule2 = new JTextArea();
        taQueueModule2.setEditable(false);
        spModule2 = new JScrollPane(taQueueModule2);
        spModule2.setViewportView(taQueueModule2);
        spModule2.setBounds(15, 185, 271, 95);
        
        taDataModule3 = new JTextArea();
        taDataModule3.setEditable(false);
        taDataModule3.setBounds(15, 125, 271, 50);
        
        taQueueModule3 = new JTextArea();
        taQueueModule3.setEditable(false);
        spModule3 = new JScrollPane(taQueueModule3);
        spModule3.setViewportView(taQueueModule3);
        spModule3.setBounds(15, 185, 271, 95);

    }

    public void agregar() {
        contentPane.add(panelModulo3_GenerarTurno);
        panelModulo3_GenerarTurno.add(panelRetiros, "cell 0 0,grow");

        panelRetiros.add(lblNewLabel_4);
        panelRetiros.add(lblNewLabel_2_1);
        panelRetiros.add(txtTurnoActualModulo3);

        panelModulo3_GenerarTurno.add(panelGenerarTurno, "cell 0 1,grow");
        panelGenerarTurno.add(lblNewLabel_5);
        panelGenerarTurno.add(lblNewLabel_6);
        panelGenerarTurno.add(txtCedulaGenerarTurno);
        panelGenerarTurno.add(btnGenerarTurno);
        panelGenerarTurno.add(lblNewLabel_7);
        panelGenerarTurno.add(btnEstadisticas);
        panelGenerarTurno.add(lblNewLabel_8);
        panelGenerarTurno.add(cmbTramite);

        contentPane.add(panelModulo1_2);
        panelModulo1_2.add(panelPrestamos, "cell 0 0,grow");

        panelPrestamos.add(lblNewLabel);

        panelPrestamos.add(lblNewLabel_2);
        panelPrestamos.add(txtTurnoActualModulo1);

        panelModulo1_2.add(panelAsesorias, "cell 0 2 1 3,grow");
        panelAsesorias.add(lblNewLabel_1);

        panelAsesorias.add(lblNewLabel_3);
        panelAsesorias.add(txtTurnoActualModulo2);

        contentPane.add(panelnformacion);
        
        panelnformacion.add(lblNewLabel_4_1);
        contentPane.add(panelPerson);
        
        panelPrestamos.add(taDataModule1);
        panelPrestamos.add(spModule1);
        
        panelAsesorias.add(taDataModule2);
        panelAsesorias.add(spModule2);
        
        panelRetiros.add(taDataModule3);
        panelRetiros.add(spModule3);

    }
    
    public void estilos() {

        panelModulo3_GenerarTurno.setBackground(Color.DARK_GRAY);

        panelRetiros.setBackground(new Color(217, 108, 137));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 35));

        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

        txtTurnoActualModulo3.setText("El turno actual es: ");

        txtTurnoActualModulo3.setEditable(false);
        txtTurnoActualModulo3.setHorizontalAlignment(SwingConstants.LEFT);
        txtTurnoActualModulo3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTurnoActualModulo3.setColumns(13);

        panelGenerarTurno.setBackground(new Color(240, 240, 129));
        panelGenerarTurno.setLayout(null);

        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(86, 11, 177, 25);

        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(23, 66, 107, 25);

        txtCedulaGenerarTurno.setBounds(140, 70, 140, 20);
        txtCedulaGenerarTurno.setColumns(10);

        btnGenerarTurno.setBackground(new Color(25,135,84));
        btnGenerarTurno.setBorder(UIManager.getBorder("Button.border"));
        btnGenerarTurno.setBounds(160, 129, 89, 23);

        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_7.setBounds(111, 177, 162, 37);

        btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnEstadisticas.setBounds(70, 225, 215, 30);

        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_8.setBounds(23, 108, 89, 14);

        cmbTramite.setBounds(140, 101, 140, 22);

        panelModulo1_2.setBackground(Color.DARK_GRAY);

        panelPrestamos.setBackground(new Color(179,204,87));

        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));

        txtTurnoActualModulo1.setHorizontalAlignment(SwingConstants.LEFT);
        txtTurnoActualModulo1.setFont(new Font("Tahoma", Font.PLAIN, 13));

        txtTurnoActualModulo1.setText("El turno actual es: ");
        txtTurnoActualModulo1.setEditable(false);
        txtTurnoActualModulo1.setColumns(13);

        panelAsesorias.setBackground(new Color(255, 190, 64));

        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));

        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));

        txtTurnoActualModulo2.setHorizontalAlignment(SwingConstants.LEFT);
        txtTurnoActualModulo2.setFont(new Font("Tahoma", Font.PLAIN, 13));


        txtTurnoActualModulo2.setText("El turno actual es: ");
        txtTurnoActualModulo3.setEditable(false);
        txtTurnoActualModulo2.setColumns(13);

        panelnformacion.setBackground(new Color(239, 184, 16));

        lblNewLabel_4_1.setFont(new Font("Century Gothic", Font.PLAIN, 42));


    }

    public JTextField getTxtTurnoActualModulo1() {
        return txtTurnoActualModulo1;
    }

    public void setTxtTurnoActualModulo1(JTextField txtTurnoActualModulo1) {
        this.txtTurnoActualModulo1 = txtTurnoActualModulo1;
    }

    public JTextField getTxtTurnoActualModulo2() {
        return txtTurnoActualModulo2;
    }

    public void setTxtTurnoActualModulo2(JTextField txtTurnoActualModulo2) {
        this.txtTurnoActualModulo2 = txtTurnoActualModulo2;
    }

    public JTextField getTxtTurnoActualModulo3() {
        return txtTurnoActualModulo3;
    }

    public void setTxtTurnoActualModulo3(JTextField txtTurnoActualModulo3) {
        this.txtTurnoActualModulo3 = txtTurnoActualModulo3;
    }

    public JTextField getTxtCedulaGenerarTurno() {
        return txtCedulaGenerarTurno;
    }

    public void setTxtCedulaGenerarTurno(JTextField txtCedulaGenerarTurno) {
        this.txtCedulaGenerarTurno = txtCedulaGenerarTurno;
    }

    public JComboBox getCmbTramite() {
        return cmbTramite;
    }

    public void setCmbTramite(JComboBox cmbTramite) {
        this.cmbTramite = cmbTramite;
    }

    public JButton getBtnGenerarTurno() {
        return btnGenerarTurno;
    }

    public void setBtnGenerarTurno(JButton btnGenerarTurno) {
        this.btnGenerarTurno = btnGenerarTurno;
    }

    public JButton getBtnEstadisticas() {
        return btnEstadisticas;
    }

    public void setBtnEstadisticas(JButton btnEstadisticas) {
        this.btnEstadisticas = btnEstadisticas;
    }


    @Override
    public String[] captureData(String section) {
        if (section.equals(Actioner.CAPTURARDATOS)){
            String[] data={
                    getTxtCedulaGenerarTurno().getText(),
                    "" + getCmbTramite().getSelectedItem()
            };
            getTxtCedulaGenerarTurno().setText("");
            return data;
        }


        return null;
    }

    @Override
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    public void setTurnWithPeople(ArrayList<TurnWithPerson> turnWithPeople) {
        this.turnWithPeople = turnWithPeople;
    }

    public ArrayList<TurnWithPerson> getTurnWithPeople() {
        return turnWithPeople;
    }

    public void updateFrame(){
    	PersonView personView;
    	PersonView personInTurn;
        if (turnWithPeople.get(0).existPerson()){
        	personInTurn = turnWithPeople.get(0).getPerson();
            txtTurnoActualModulo1.setText("El turno actual es: G" + personInTurn.getTurn());
            taDataModule1.setText("Nombre:"+personInTurn.getName()+"\nCedula:"+personInTurn.getId()+"\nFecha de nacimiento:"+personInTurn.getDate());
            if (turnWithPeople.get(0).getPersonViews().size()>0) {
            	taQueueModule1.setText("");
            	for (int i=0; i<turnWithPeople.get(0).getPersonViews().size(); i++) {
                	personView =turnWithPeople.get(0).getPersonViews().get(i);
                	taQueueModule1.append(personView.getName()+"  "+personView.getId()+"   "+personView.getDate()+"   A"+personView.getTurn()+"\n");
        		}
			}else {
				taQueueModule1.setText("");
			}
        }else {
            txtTurnoActualModulo1.setText("MODULO LIBRE");
            taDataModule1.setText("Nombre:\nCedula:\nFecha de nacimiento:");
        }

        if (turnWithPeople.get(1).existPerson()){
        	personInTurn = turnWithPeople.get(1).getPerson();
            txtTurnoActualModulo2.setText("El turno actual es: A" + personInTurn.getTurn());
            taDataModule2.setText("Nombre:"+personInTurn.getName()+"\nCedula:"+personInTurn.getId()+"\nFecha de nacimiento:"+personInTurn.getDate());
            if (turnWithPeople.get(1).getPersonViews().size()>0) {
            	taQueueModule2.setText("");
            	for (int i=0; i<turnWithPeople.get(1).getPersonViews().size(); i++) {
                	personView =turnWithPeople.get(1).getPersonViews().get(i);
                	taQueueModule2.append(personView.getName()+"  "+personView.getId()+"   "+personView.getDate()+"   A"+personView.getTurn()+"\n");
        		}
			}else {
				taQueueModule2.setText("");
			}
        }else{
            txtTurnoActualModulo2.setText("MODULO LIBRE"); 
            taDataModule2.setText("Nombre:\nCedula:\nFecha de nacimiento:");
        }

        if (turnWithPeople.get(2).existPerson()){
        	personInTurn = turnWithPeople.get(2).getPerson();
            txtTurnoActualModulo3.setText("El turno actual es: " + personInTurn.getTurn());
            taDataModule3.setText("Nombre:"+personInTurn.getName()+"\nCedula:"+personInTurn.getId()+"\nFecha de nacimiento:"+personInTurn.getDate()+"\n");
            if (turnWithPeople.get(2).getPersonViews().size()>0) {
            	taQueueModule3.setText("");
            	for (int i=0; i<turnWithPeople.get(2).getPersonViews().size(); i++) {
                	personView =turnWithPeople.get(2).getPersonViews().get(i);
                	taQueueModule3.append(personView.getName()+"  "+personView.getId()+"  "+personView.getDate()+"   "+personView.getTurn()+"\n");
        		}
			}else {
				taQueueModule3.setText("");
			}
        }else {
            txtTurnoActualModulo3.setText("MODULO LIBRE");
            taDataModule3.setText("Nombre:\nCedula:\nFecha de nacimiento:");
        }
        

        repaint();

    }


}
