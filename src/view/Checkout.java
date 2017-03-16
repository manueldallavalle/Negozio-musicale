package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import model.Funzioni;
import model.JTextFieldLimit;

import javax.swing.JRadioButton;

public class Checkout extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel pannelloCheckout;
	private JLabel lblPesoTot;
	private JScrollPane scrollPane;
	private HashMap<Integer,JTextField> mapTextfield;
	private JLabel lblTotale;
	private JLabel lblTotPrezzo;
	private JTextField txtIndirizzo;
	private JTextField txtNIndirizzo;
	private JTextField txtCitta;
	private JTextField txtCap;
	private JPanel pannelloSpedizione;
	private JButton btnVaiPagamento;
	private JLabel icoIndirizzo;
	private JLabel icoCitta;
	private JLabel icoCap;
	private JButton btnAnnulla;
	private JButton btnVaiSpedizione;
	private JButton btnVaiCarrello;
	private JRadioButton radioSDA;
	private JRadioButton radioBartolini;
	private JRadioButton radioGLS;
	private float speseSpedizione;
	private JLabel lblPrezzoSpedizione;
	private JLabel lblPrezzoTot;
	private JPanel pannelloPagamento;
	private JTextField txtCartaUno;
	private JTextField txtCartaDue;
	private JTextField txtCartaTre;
	private JTextField txtCartaQuattro;
	private JTextField txtIntestario;
	private JTextField txtCVV;
	private JLabel icoVIntestario;
	private JLabel icoVCarta;
	private JLabel icoVCVV;
	private JButton btnConfermaPagamento;
	private JButton btnTornaSpedizione;
	private JRadioButton radioPayPal;
	private JRadioButton radioCartaCredito;
	private JRadioButton radioBonifico;
	private JTextField txtRicevutaBonifico;
	private JLabel icoRicevutaBonifico;
	private JLabel icoVerificaPagamento;
	private JLabel lblVerificaPagamento;
	private String tipoPagamento;
	private JLabel lblCodice;
	private JLabel lblIntestario;
	private JLabel lblCVV;
	private JLabel icoCVV;
	private JLabel lblIstruzioneUno;
	private JLabel lblTotaleBonifico;
	private JLabel lblIstruzioneUnoA;
	private JLabel lblIstruzioneUnoB;
	private JLabel lblIstruzioneDue;
	private String strIndirizzo;
	private String strCivico;
	private String strCitta;
	private String strCap;
	private String tipoCorriere;
	private Boolean utenteSpeciale;
	private JLabel lblSubTotale;
	private JLabel lblSpedizioneTratt;
	private JLabel lblPrezzoSub;
	private JLabel icoInfo;

	public Checkout() {
		setLayout(null);
		setPreferredSize(new Dimension(799, 448));
		setOpaque(false);
		
		strIndirizzo = "";
		strCivico = "";
		strCitta = "";
		strCap = "";

		creaHeader();
		creaPanCheckout();
	}
	
	private void creaHeader(){
        JPanel pannelloHeader = new JPanel();
        pannelloHeader.setBorder(new LineBorder(new Color(0, 0, 0)));
        pannelloHeader.setLayout(null);
        pannelloHeader.setBounds(25, 47, 610, 55);
        add(pannelloHeader);
        
        JLabel imgH = new JLabel("Immagine");
        imgH.setBounds(16, 19, 86, 16);
        pannelloHeader.add(imgH);
        
        JLabel nomeH = new JLabel("Nome Prodotto");
        nomeH.setBounds(135, 19, 123, 16);
        pannelloHeader.add(nomeH);
        
        JLabel quantH = new JLabel("Quantit\u00E0");
        quantH.setBounds(320, 19, 61, 16);
        pannelloHeader.add(quantH);
        
        JLabel totH = new JLabel("Totale");
        totH.setBounds(493, 19, 61, 16);
        pannelloHeader.add(totH);
	}
	
	private void creaPanCheckout(){
		mapTextfield = new HashMap<Integer,JTextField>();
		
		lblPesoTot = new JLabel("CARRELLO DELLA SPESA (0,00 KG)");
		lblPesoTot.setFont(new Font("Arial", Font.BOLD, 15)); 
		lblPesoTot.setBounds(25, 19, 439, 16);
		add(lblPesoTot);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 102, 610, 221);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		pannelloCheckout = new JPanel();
		scrollPane.setViewportView(pannelloCheckout);
		pannelloCheckout.setLayout(null);
		pannelloCheckout.setBackground(Color.WHITE); 
		
        lblTotale = new JLabel("Totale:");
        lblTotale.setFont(new Font("Arial", Font.BOLD, 15)); 
        add(lblTotale);

        lblTotPrezzo = new JLabel(String.format("%.2f \u20ac", 0.00));
        lblTotPrezzo.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblTotPrezzo.setForeground(Color.decode("#990000"));
        add(lblTotPrezzo);
        
        btnAnnulla = new JButton("Continua con gli acquisti");
       	add(btnAnnulla);
        
        btnVaiSpedizione = new JButton("Conferma l'ordine");
        add(btnVaiSpedizione);
        btnVaiSpedizione.setVisible(false);
	}
	
	private void creaPanSpedizione(){
        pannelloSpedizione = new JPanel();
        pannelloSpedizione.setBounds(25, 15, 610, 289);
        add(pannelloSpedizione);
        pannelloSpedizione.setLayout(null);
        
        JSeparator separatoreUno = new JSeparator();
        separatoreUno.setBounds(6, 10, 9, 12);
        pannelloSpedizione.add(separatoreUno);
        
        JLabel lblSeparatoreConsegna = new JLabel("Dettagli consegna");
        lblSeparatoreConsegna.setBounds(21, 6, 93, 16);
        pannelloSpedizione.add(lblSeparatoreConsegna);
        lblSeparatoreConsegna.setFont(new Font("Arial", Font.PLAIN, 10));
        
        JSeparator separatoreDue = new JSeparator();
        separatoreDue.setBounds(113, 10, 490, 12);
        pannelloSpedizione.add(separatoreDue);
        
        JLabel lblIndirizzo = new JLabel("Indirizzo:");
        lblIndirizzo.setBounds(6, 39, 61, 16);
        pannelloSpedizione.add(lblIndirizzo);
        
        txtIndirizzo = new JTextField(strIndirizzo);
        txtIndirizzo.setBounds(70, 34, 147, 26);
        pannelloSpedizione.add(txtIndirizzo);
        txtIndirizzo.setColumns(10);
        
        JLabel lblNIndirizzo = new JLabel("N:");
        lblNIndirizzo.setBounds(222, 39, 28, 16);
        pannelloSpedizione.add(lblNIndirizzo);
        
        txtNIndirizzo = new JTextField(strCivico);
        txtNIndirizzo.setBounds(250, 34, 41, 26);
        pannelloSpedizione.add(txtNIndirizzo);
        txtNIndirizzo.setColumns(10);
        
        JLabel lblCitta = new JLabel("Citt\u00E0:");
        lblCitta.setBounds(6, 72, 61, 16);
        pannelloSpedizione.add(lblCitta);
        
        txtCitta = new JTextField(strCitta);
        txtCitta.setColumns(10);
        txtCitta.setBounds(70, 67, 221, 26);
        pannelloSpedizione.add(txtCitta);
        
        JLabel lblCap = new JLabel("CAP:");
        lblCap.setBounds(6, 105, 61, 16);
        pannelloSpedizione.add(lblCap);
        
        txtCap = new JTextField();
        txtCap.setColumns(10);
        txtCap.setBounds(70, 100, 74, 26);
        txtCap.setDocument(new JTextFieldLimit(5));
        txtCap.setText(strCap);
        pannelloSpedizione.add(txtCap);
        
        icoIndirizzo = new JLabel("");
        icoIndirizzo.setBounds(303, 39, 61, 16);
        pannelloSpedizione.add(icoIndirizzo);
        
        icoCitta = new JLabel("");
        icoCitta.setBounds(303, 72, 61, 16);
        pannelloSpedizione.add(icoCitta);
        
        icoCap = new JLabel("");
        icoCap.setBounds(303, 105, 61, 16);
        pannelloSpedizione.add(icoCap);
        
        JLabel lblTipoCorriere = new JLabel("Tipo corriere:");
        lblTipoCorriere.setBounds(457, 39, 109, 16);
        pannelloSpedizione.add(lblTipoCorriere);
        
        ButtonGroup bTipoCorriere = new ButtonGroup();
        speseSpedizione = 8;
        tipoCorriere = "SDA";
        
        radioSDA = new JRadioButton("SDA (8.00 \u20ac)");
        radioSDA.setBounds(450, 68, 141, 23);
        radioSDA.setSelected(true);
        radioSDA.setName("8.00#SDA");
        pannelloSpedizione.add(radioSDA);
        bTipoCorriere.add(radioSDA);
        
        radioBartolini = new JRadioButton("Bartolini (10.00 \u20ac)");
        radioBartolini.setBounds(450, 101, 153, 23);
        radioBartolini.setName("10.00#Bartolini");
        pannelloSpedizione.add(radioBartolini);
        bTipoCorriere.add(radioBartolini);
        
        radioGLS = new JRadioButton("GLS (10.00 \u20ac)");
        radioGLS.setBounds(450, 131, 141, 23);
        radioGLS.setName("10.00#GLS");
        pannelloSpedizione.add(radioGLS);
        bTipoCorriere.add(radioGLS);
	        
       	/** UTENTE SPECIALE !!
        	speseSpedizione = 4;
	        tipoCorriere = "SDA";
	        
	        radioSDA = new JRadioButton("SDA (8.00 \u20ac)");
	        radioSDA.setBounds(450, 68, 141, 23);
	        radioSDA.setSelected(true);
	        radioSDA.setName("4.00#SDA");
	        pannelloSpedizione.add(radioSDA);
	        bTipoCorriere.add(radioSDA);
	        
	        radioBartolini = new JRadioButton("Bartolini (10.00 \u20ac)");
	        radioBartolini.setBounds(450, 101, 153, 23);
	        radioBartolini.setName("5.00#Bartolini");
	        pannelloSpedizione.add(radioBartolini);
	        bTipoCorriere.add(radioBartolini);
	        
	        radioGLS = new JRadioButton("GLS (10.00 \u20ac)");
	        radioGLS.setBounds(450, 131, 141, 23);
	        radioGLS.setName("5.00#GLS");
	        pannelloSpedizione.add(radioGLS);
	        bTipoCorriere.add(radioGLS);
	        
	        radioSDA.addActionListener(this);
	        radioBartolini.addActionListener(this);
	        radioGLS.addActionListener(this);
        */
        
        lblSubTotale = new JLabel("Subtotale:");
        lblSubTotale.setBounds(365, 196, 166, 16);
        pannelloSpedizione.add(lblSubTotale);
        
        lblSpedizioneTratt = new JLabel();
        lblSpedizioneTratt.setText("Spedizione e trattamento:");
        lblSpedizioneTratt.setBounds(365, 224, 166, 16);
        pannelloSpedizione.add(lblSpedizioneTratt);
        
        lblPrezzoSub = new JLabel("0.00 \u20ac");

        lblPrezzoSub.setBounds(530, 196, 73, 16);
        lblPrezzoSub.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblPrezzoSub.setForeground(Color.decode("#990000"));
        pannelloSpedizione.add(lblPrezzoSub);
        
        lblPrezzoSpedizione = new JLabel(String.format("%.2f \u20ac", speseSpedizione));
        lblPrezzoSpedizione.setBounds(530, 224, 73, 16);
        lblPrezzoSpedizione.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblPrezzoSpedizione.setForeground(Color.decode("#990000"));
        pannelloSpedizione.add(lblPrezzoSpedizione);
        
        JLabel lblTotaleCompl = new JLabel("Totale complessivo:");
        lblTotaleCompl.setBounds(365, 252, 141, 16);
        pannelloSpedizione.add(lblTotaleCompl);
        
        lblPrezzoTot = new JLabel("0.00 \u20ac");
        lblPrezzoTot.setBounds(530, 252, 73, 16);
        lblPrezzoTot.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblPrezzoTot.setForeground(Color.decode("#990000"));
        pannelloSpedizione.add(lblPrezzoTot);
                
        pannelloSpedizione.add(lblTotaleCompl);
                
        btnVaiPagamento = new JButton("Vai al pagamento");
        btnVaiPagamento.setBounds(21, 237, 270, 31);
        pannelloSpedizione.add(btnVaiPagamento);
        
        btnVaiCarrello = new JButton("Torna al carrello");
        btnVaiCarrello.setBounds(21, 196, 270, 29);
        pannelloSpedizione.add(btnVaiCarrello);

        JSeparator separator = new JSeparator();
        separator.setBounds(6, 172, 597, 12);
        pannelloSpedizione.add(separator);
        
        JSeparator separator_1 = new JSeparator(SwingConstants.VERTICAL);
        separator_1.setBounds(323, 177, 41, 112);
        pannelloSpedizione.add(separator_1);
        
        icoInfo = new JLabel();
        icoInfo.setBounds(339, 224, 16, 16);
        icoInfo.setIcon(new ImageIcon("img/info.png"));
        icoInfo.setToolTipText("Grazie ai tuoi precedenti acquisti, puoi usufruire della spedizione gratuita");
        icoInfo.setVisible(false);
        pannelloSpedizione.add(icoInfo);
	}
	
	private void creaPanPagamento(){
        pannelloPagamento = new JPanel();
        pannelloPagamento.setBounds(25, 15, 459, 263);
        add(pannelloPagamento);
        pannelloPagamento.setLayout(null);
		
        JSeparator separatoreUno = new JSeparator();
        separatoreUno.setBounds(6, 10, 9, 12);
        pannelloPagamento.add(separatoreUno);
        
        JLabel lblSeparatoreConsegna = new JLabel("Dettagli pagamento");
        lblSeparatoreConsegna.setBounds(21, 6, 93, 16);
        pannelloPagamento.add(lblSeparatoreConsegna);
        lblSeparatoreConsegna.setFont(new Font("Arial", Font.PLAIN, 10));
        
        JSeparator separatoreDue = new JSeparator();
        separatoreDue.setBounds(121, 10, 482, 12);
        pannelloPagamento.add(separatoreDue);
        
        JLabel lblMetodoPagamento = new JLabel("Seleziona il metodo di pagamento:");
        lblMetodoPagamento.setBounds(16, 34, 225, 16);
        pannelloPagamento.add(lblMetodoPagamento);
        
        ButtonGroup bTipoPagamento = new ButtonGroup();
        
        radioPayPal = new JRadioButton();
        radioPayPal.setBounds(43, 62, 54, 23);
        radioPayPal.setSelected(true);
        radioPayPal.setName("PayPal");
        pannelloPagamento.add(radioPayPal);
        bTipoPagamento.add(radioPayPal);

        radioCartaCredito = new JRadioButton();
        radioCartaCredito.setBounds(206, 62, 54, 23);
        radioCartaCredito.setName("Carta di credito");
        pannelloPagamento.add(radioCartaCredito);
        bTipoPagamento.add(radioCartaCredito);

        radioBonifico = new JRadioButton();
        radioBonifico.setBounds(369, 62, 46, 23);
        radioBonifico.setName("Bonifico");
        pannelloPagamento.add(radioBonifico);
        bTipoPagamento.add(radioBonifico);

        JLabel icoPaypal = new JLabel();
        icoPaypal.setBounds(21, 85, 128, 68);
        icoPaypal.setIcon(new ImageIcon("img/pagamento_paypal.png"));
        pannelloPagamento.add(icoPaypal);
        
        JLabel icoCartaCredito = new JLabel();
        icoCartaCredito.setBounds(184, 85, 128, 68);
        icoCartaCredito.setIcon(new ImageIcon("img/pagamento_cartacredito.png"));
        pannelloPagamento.add(icoCartaCredito);
        
        JLabel icoBonifico = new JLabel();
        icoBonifico.setBounds(347, 85, 128, 68);
        icoBonifico.setIcon(new ImageIcon("img/pagamento_bonifico.png"));
        pannelloPagamento.add(icoBonifico);
        
        btnTornaSpedizione = new JButton("Torna ai dettagli spedizione");
        btnTornaSpedizione.setBounds(6, 210, 205, 29);
        pannelloPagamento.add(btnTornaSpedizione);

        btnConfermaPagamento = new JButton("Conferma il pagamento");
        btnConfermaPagamento.setBounds(223, 210, 217, 29);
        pannelloPagamento.add(btnConfermaPagamento);

        icoVerificaPagamento = new JLabel();
        icoVerificaPagamento.setBounds(21, 182, 20, 16);
        icoVerificaPagamento.setIcon(new ImageIcon("img/caricamento.gif"));
        pannelloPagamento.add(icoVerificaPagamento);
        
        lblVerificaPagamento = new JLabel("Verifica in corso del pagamento");
        lblVerificaPagamento.setBounds(53, 182, 158, 16);
        lblVerificaPagamento.setFont(new Font("Arial", Font.ITALIC, 10));
        pannelloPagamento.add(lblVerificaPagamento);
       
        icoVerificaPagamento.setVisible(false);
        lblVerificaPagamento.setVisible(false);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(6, 161, 451, 15);
        pannelloPagamento.add(separator);
        
        tipoPagamento = "PayPal";
	}

	public void cambiaPagamento(String tipoAgg){

		// RIMUOVI PANNELLO PRECEDENTE
		if(tipoPagamento.equals("Carta di credito")){
			pannelloPagamento.remove(lblCodice);
			pannelloPagamento.remove(txtCartaUno);
			pannelloPagamento.remove(txtCartaDue);
			pannelloPagamento.remove(txtCartaTre);
			pannelloPagamento.remove(txtCartaQuattro);
			pannelloPagamento.remove(lblIntestario);
			pannelloPagamento.remove(txtIntestario);
			pannelloPagamento.remove(lblCVV);
			pannelloPagamento.remove(txtCVV);
			pannelloPagamento.remove(icoCVV);
			pannelloPagamento.remove(icoVIntestario);
			pannelloPagamento.remove(icoVCarta);
			pannelloPagamento.remove(icoVCVV);
		}else if(tipoPagamento.equals("Bonifico")){
			pannelloPagamento.remove(lblIstruzioneUno);
			pannelloPagamento.remove(lblTotaleBonifico);
			pannelloPagamento.remove(lblIstruzioneUnoA);
			pannelloPagamento.remove(lblIstruzioneUnoB);
			pannelloPagamento.remove(lblIstruzioneDue);
			pannelloPagamento.remove(txtRicevutaBonifico);
			pannelloPagamento.remove(icoRicevutaBonifico);
		}
				
		if(tipoAgg.equals("PayPal")){
			setPreferredSize(new Dimension(513, 285));
	        pannelloPagamento.setBounds(25, 15, 459, 263);
			btnTornaSpedizione.setBounds(6, 210, 205, 29);
			btnConfermaPagamento.setBounds(223, 210, 217, 29);
			icoVerificaPagamento.setBounds(21, 182, 20, 16);
			lblVerificaPagamento.setBounds(53, 182, 158, 16);
		}else if(tipoAgg.equals("Carta di credito")){
	        lblCodice = new JLabel("Codice carta:");
	        lblCodice.setBounds(21, 213, 93, 16);
	        pannelloPagamento.add(lblCodice);
	        
	        txtCartaUno = new JTextField();
	        txtCartaUno.setBounds(197, 208, 61, 26);
	        pannelloPagamento.add(txtCartaUno);
	        txtCartaUno.setDocument(new JTextFieldLimit(4));
	        txtCartaUno.setColumns(10);
	        
	        txtCartaDue = new JTextField();
	        txtCartaDue.setBounds(270, 208, 61, 26);
	        pannelloPagamento.add(txtCartaDue);
	        txtCartaDue.setDocument(new JTextFieldLimit(4));
	        txtCartaDue.setColumns(10);
	        
	        txtCartaTre = new JTextField();
	        txtCartaTre.setColumns(10);
	        txtCartaTre.setBounds(124, 208, 61, 26);
	        txtCartaTre.setDocument(new JTextFieldLimit(4));
	        pannelloPagamento.add(txtCartaTre);
	        
	        txtCartaQuattro = new JTextField();
	        txtCartaQuattro.setColumns(10);
	        txtCartaQuattro.setBounds(343, 208, 61, 26);
	        txtCartaQuattro.setDocument(new JTextFieldLimit(4));
	        pannelloPagamento.add(txtCartaQuattro);
	        
	        lblIntestario = new JLabel("Intestario:");
	        lblIntestario.setBounds(21, 179, 100, 16);
	        pannelloPagamento.add(lblIntestario);
	        
	        txtIntestario = new JTextField();
	        txtIntestario.setBounds(124, 174, 280, 26);
	        pannelloPagamento.add(txtIntestario);
	        txtIntestario.setColumns(10);
	        
	        lblCVV = new JLabel("CVV:");
	        lblCVV.setBounds(21, 246, 61, 16);
	        pannelloPagamento.add(lblCVV);
	        
	        txtCVV = new JTextField();
	        txtCVV.setBounds(124, 241, 61, 26);
	        pannelloPagamento.add(txtCVV);
	        txtCVV.setDocument(new JTextFieldLimit(3));
	        txtCVV.setColumns(10);
	        
	        icoCVV = new JLabel();
	        icoCVV.setBounds(199, 230, 61, 37);
	        icoCVV.setIcon(Funzioni.ridimensionaImg(new ImageIcon("img/pagamento_cvv.png"), 55, 35));
	        pannelloPagamento.add(icoCVV);
	        
	        icoVIntestario = new JLabel();
	        icoVIntestario.setBounds(416, 179, 61, 16);
	        pannelloPagamento.add(icoVIntestario);
	        
	        icoVCarta = new JLabel();
	        icoVCarta.setBounds(416, 213, 61, 16);
	        pannelloPagamento.add(icoVCarta);
	        
	        icoVCVV = new JLabel();
	        icoVCVV.setBounds(280, 244, 85, 23);
	        pannelloPagamento.add(icoVCVV);
		}else if(tipoAgg.equals("Bonifico")){
	        lblIstruzioneUno = new JLabel("1. Effettua un bonifico di ");
	        lblIstruzioneUno.setBounds(21, 193, 161, 16);
	        pannelloPagamento.add(lblIstruzioneUno);
	        
	        lblTotaleBonifico = new JLabel("0.00 \u20ac");
	        lblTotaleBonifico.setBounds(184, 194, 61, 16);
	        lblTotaleBonifico.setFont(new Font("Arial", Font.BOLD, 11)); 
	        lblTotaleBonifico.setForeground(Color.decode("#990000"));
	        pannelloPagamento.add(lblTotaleBonifico);

	        lblIstruzioneUnoA = new JLabel("al conto");
	        lblIstruzioneUnoA.setBounds(234, 193, 61, 16);
	        pannelloPagamento.add(lblIstruzioneUnoA);
	        
	        lblIstruzioneUnoB = new JLabel("IT11X0326810001100000000000");
	        lblIstruzioneUnoB.setBounds(291, 194, 185, 16);
	        lblIstruzioneUnoB.setFont(new Font("Arial", Font.BOLD, 10));
	        pannelloPagamento.add(lblIstruzioneUnoB);
	        
	        lblIstruzioneDue = new JLabel("2. Inserisci il codice di ricevuta:");
	        lblIstruzioneDue.setBounds(21, 221, 205, 16);
	        pannelloPagamento.add(lblIstruzioneDue);
	        
	        txtRicevutaBonifico = new JTextField();
	        txtRicevutaBonifico.setBounds(223, 216, 192, 26);
	        pannelloPagamento.add(txtRicevutaBonifico);
	        txtRicevutaBonifico.setColumns(10);
	        
	        icoRicevutaBonifico = new JLabel();
	        icoRicevutaBonifico.setBounds(427, 221, 61, 16);
	        pannelloPagamento.add(icoRicevutaBonifico);
		}
		
		if(tipoAgg.equals("Carta di credito") || tipoAgg.equals("Bonifico")){
			setPreferredSize(new Dimension(513, 376));
			pannelloPagamento.setBounds(25, 15, 459, 355);
			btnTornaSpedizione.setBounds(6, 307, 205, 29);
			btnConfermaPagamento.setBounds(223, 307, 217, 29);
			icoVerificaPagamento.setBounds(21, 279, 20, 16);
			lblVerificaPagamento.setBounds(53, 279, 158, 16);
		}
		
		pannelloPagamento.repaint();
		pannelloPagamento.revalidate();
	}
	
	/**				
	private boolean checkCampi(int tipo){
		// TIPO 1 = SPEDIZIONE
		// TIPO 2 = PAGAMENTO CARTA
		// TIPO 3 = PAGAMENTO BONIFICO
		if(tipo == 1){
			boolean indirizzoV = txtIndirizzo.getText().isEmpty();
			boolean civicoV = !Funzioni.isInteger(txtNIndirizzo.getText()) || txtNIndirizzo.getText().isEmpty();
			boolean cittaV = txtCitta.getText().isEmpty();
			boolean capV = !Funzioni.isInteger(txtCap.getText()) || txtCap.getText().isEmpty() || txtCap.getText().length() < 5;
	
			icoIndirizzo.setIcon(indirizzoV || civicoV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			icoCitta.setIcon(cittaV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			icoCap.setIcon(capV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			
			return !(indirizzoV || civicoV || cittaV || capV);
		}else if(tipo == 2){
			boolean intestarioV = txtIntestario.getText().isEmpty();
			boolean cartaUnoV = !Funzioni.isInteger(txtCartaUno.getText()) || txtCartaUno.getText().length() < 4;
			boolean cartaDueV = !Funzioni.isInteger(txtCartaDue.getText()) || txtCartaDue.getText().length() < 4;
			boolean cartaTreV = !Funzioni.isInteger(txtCartaTre.getText()) || txtCartaUno.getText().length() < 4;
			boolean cartaQuattroV = !Funzioni.isInteger(txtCartaQuattro.getText()) || txtCartaQuattro.getText().length() < 4;
			boolean cvvV = !Funzioni.isInteger(txtCVV.getText()) || txtCVV.getText().length() < 3;
	
			icoVIntestario.setIcon(intestarioV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			icoVCarta.setIcon(cartaUnoV || cartaDueV || cartaTreV || cartaQuattroV  ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			icoVCVV.setIcon(cvvV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			
			return !(intestarioV || cartaUnoV || cartaDueV || cartaTreV || cartaQuattroV || cvvV);
		}else if(tipo == 3){
			boolean ricevutaV = txtRicevutaBonifico.getText().isEmpty();
			icoRicevutaBonifico.setIcon(ricevutaV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			
			return !ricevutaV;
		}
		return true;
	}
		
	private void venditaTerminata(){
		Iterator<Strumento> itr = Carrello.getStrumenti().iterator();
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap <String, ArrayList<Integer>> mappa_vendute = new HashMap<String, ArrayList<Integer>>();
		
		// AGGIUNTA DELL'ACQUISTO NEL DB
	    while (itr.hasNext()) {
	    	Strumento tmp = itr.next();
	    	if(!list.contains(tmp.getCodiceStrumento())){
	    		list.add(tmp.getCodiceStrumento());
	    		int quantita = Carrello.getNStrumenti(tmp.getCodiceStrumento());
        		int inserisciDB = DBManager.updateQuery(EnumQuery.INSERISCI_ACQUISTO.getValore(), false,
        				tmp.getCodiceStrumento(),
        				framePrincipale.getUtenteGlobale().getUsername(),
        				tmp.getPrezzo() * quantita,
        				Funzioni.getCurrTimestamp(),
        				Funzioni.getCurrTimestamp(),
        				framePrincipale.getUtenteGlobale().getIndirizzoIP(),
        				tipoPagamento,
        				tipoCorriere,
        				quantita
        				);
        		if(inserisciDB <= 0){
        			JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'acquisto nel DB!");
        		}
	    	}
	    }
	    
	    // INVIO E-MAIL AL VENDITORE
		Iterator<Integer> itr_codice = list.iterator();
	    while (itr_codice.hasNext()) {
	    	int tmp = itr_codice.next();
	    		List<HashMap<String,Object>> risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTI_DA_CLIENTE.getValore(),
	    				""+tmp); 
	    		Iterator<HashMap<String,Object>> itr_query = risultatoQuery.iterator();
	    		while (itr_query.hasNext()) {
	    	    	HashMap<String,Object> riga = itr_query.next();
	    	    	ArrayList<Integer> arrcodicivenduti = mappa_vendute.get(riga.get("email").toString());
	    	    	if(arrcodicivenduti == null){
	    	    		arrcodicivenduti = new ArrayList<Integer>();
	    	    		mappa_vendute.put(riga.get("email").toString(), arrcodicivenduti);
	    	    	}
	    	    	arrcodicivenduti.add(tmp);	    	    		
	    	    }
	    }
	    Iterator<Entry<String, ArrayList<Integer>>> itr_mappe = mappa_vendute.entrySet().iterator();
	    while (itr_mappe.hasNext()) {
	    	Entry<String, ArrayList<Integer>> el = itr_mappe.next();
	    	if(!Funzioni.InviaMailVendita(el.getKey(), framePrincipale.getUtenteGlobale(), el.getValue(), tipoPagamento, tipoCorriere, strIndirizzo+", "+strCivico, strCitta+" ("+strCap+")")){
    			JOptionPane.showMessageDialog(null, "Errore nell'invio dell'e-mail al venditore!");
    		}
	    }
	    
	    // SVUOTO CARRELLO
	    Carrello.svuotaCarrello();
	    
	    // FINE PAGAMENTO
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Il tuo pagamento \u00E8 stato verificato\nGrazie per aver acquistato presso il nostro negozio!");
		    	framePrincipale.getContentPane().removeAll();
		        framePrincipale.getContentPane().add(new MenuPrincipale(framePrincipale,false));
		        framePrincipale.pack();
			}
		}, 2500); //2.5 sec
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			String nomeBtn = ((JButton) e.getSource()).getName();
			if(nomeBtn != null && nomeBtn.indexOf("btn") > -1){
				int indexArray = Integer.parseInt(nomeBtn.split("btn")[1]);
				if(nomeBtn.charAt(0) == 'R'){
					Carrello.rimuoviStrumento(indexArray);
				}else{
					if(!mapTextfield.get(indexArray).getText().isEmpty() && Funzioni.isInteger(mapTextfield.get(indexArray).getText())){
						Carrello.aggiornaQuantita(Integer.parseInt(mapTextfield.get(indexArray).getText()), indexArray);
					}
				}
				aggiornaStile();
			}else if(e.getSource() == btnVaiPagamento){
				if(checkCampi(1)){
					strIndirizzo = txtIndirizzo.getText();
					strCivico = txtNIndirizzo.getText();
					strCitta = txtCitta.getText();
					strCap = txtCap.getText();
					switchPannello(3);
				}
			}else if(e.getSource() == btnAnnulla){
	        	framePrincipale.getContentPane().removeAll();
	            framePrincipale.getContentPane().add(new MenuPrincipale(framePrincipale,false));
	            framePrincipale.pack();
			}else if(e.getSource() == btnVaiSpedizione || e.getSource() == btnTornaSpedizione){
				switchPannello(2);
			}else if(e.getSource() == btnVaiCarrello){
				switchPannello(1);
			}else if(e.getSource() == btnConfermaPagamento){
				boolean campiOk = true;
				if(radioCartaCredito.isSelected()){
					campiOk = checkCampi(2);
					if(campiOk){
						txtCartaUno.setEnabled(false);
						txtCartaDue.setEnabled(false);
						txtCartaTre.setEnabled(false);
						txtCartaQuattro.setEnabled(false);
						txtIntestario.setEnabled(false);
						txtCVV.setEnabled(false);
					}
				}else if(radioBonifico.isSelected()){
					campiOk = checkCampi(3);
					if(campiOk)	txtRicevutaBonifico.setEnabled(false);
				}
				if(campiOk){
			        icoVerificaPagamento.setVisible(true);
			        lblVerificaPagamento.setVisible(true);
			        btnConfermaPagamento.setEnabled(false);
			        btnTornaSpedizione.setEnabled(false);
			        radioPayPal.setEnabled(false);
			        radioCartaCredito.setEnabled(false);
			        radioBonifico.setEnabled(false);
			        
					Thread a = new Thread(() -> { venditaTerminata(); });			
					a.start();
				}
				
			}
		}else if(e.getSource() == radioSDA || e.getSource() == radioBartolini || e.getSource() == radioGLS){
			speseSpedizione = Float.parseFloat(((JRadioButton) e.getSource()).getName().split("#")[0]);
			tipoCorriere = ((JRadioButton) e.getSource()).getName().split("#")[1];
			aggiornaTotali();			
		}else if(e.getSource() == radioPayPal){
			impostaPagamento(1);
			tipoPagamento = "PayPal";
		}else if(e.getSource() == radioCartaCredito){
			impostaPagamento(2);
			tipoPagamento = "Carta di credito";
		}else if(e.getSource() == radioBonifico){
			impostaPagamento(3);
			tipoPagamento = "Bonifico";
		}
	}
*/
	public void aggiungiListener(ActionListener lst, int tipo){
		switch(tipo){
			case 1: btnAnnulla.addActionListener(lst);
					break;
			case 2: btnVaiSpedizione.addActionListener(lst);
					break;
			case 3: btnVaiCarrello.addActionListener(lst);
					break;
			case 4: radioSDA.addActionListener(lst);
        			radioBartolini.addActionListener(lst);
        			radioGLS.addActionListener(lst);
        			break;
			case 5: btnVaiPagamento.addActionListener(lst);
					break;
			case 6: btnTornaSpedizione.addActionListener(lst);
					break;
			case 7: radioPayPal.addActionListener(lst);
					radioCartaCredito.addActionListener(lst);
					radioBonifico.addActionListener(lst);
					break;
			case 8:	btnConfermaPagamento.addActionListener(lst);
					break;
		}
    } 
	
	public void aggiungiMappaTxt(int cont, JTextField txtQuant) {
		mapTextfield.put(cont, txtQuant);
	} 
	
	public void aggiungiLabel(JLabel lbl){
		pannelloCheckout.add(lbl);
	}
	
	public void aggiungiTextField(JTextField txt){
		pannelloCheckout.add(txt);
	}
		
	public void aggiungiBtn(JButton btn){
		pannelloCheckout.add(btn);
	}

	public void aggiungiSeparator(JSeparator separator) {
		pannelloCheckout.add(separator);
	}
		
	public void impostaPosizioniCarrello(int tipo, int x, int y, int width, int height){
		switch(tipo){
			case 1:
				lblTotale.setBounds(x,y,width,height);
				break;
			case 2:
				lblTotPrezzo.setBounds(x,y,width,height);
				break;
			case 3:
				btnAnnulla.setBounds(x,y,width,height);
				break;
			case 4:
				btnVaiSpedizione.setBounds(x,y,width,height);
				break;
			case 5:
				scrollPane.setBounds(x,y,width,height);
				break;
		}
	}
	
	public void cambiaVisualeCheckout(int tipo, int yElemento){
		// TIPO == 1 - CI SONO ELEMENTI NEL CARRELLO
		// TIPO == 2 - NON CI SONO ELEMENTI NEL CARRELLO
		if(tipo == 1){
			pannelloCheckout.setPreferredSize(new Dimension(650, yElemento - 20));
            setPreferredSize(new Dimension(660, (yElemento + 130 <= 370) ? yElemento + 130 : 370));
            scrollPane.setPreferredSize(new Dimension(645, yElemento + 50));
		}else{
            scrollPane.setBounds(25, 102, 610, 75);
            pannelloCheckout.setPreferredSize(new Dimension(650, 70));
            setPreferredSize(new Dimension(660, 222));
            scrollPane.setPreferredSize(new Dimension(645, 200));
		}
	}

	public void impostaTxtPrezzoTot(String str) {
		lblTotPrezzo.setText(str);
	}
	
	public void cambiaConfermaOrdine(boolean stato){
		btnVaiSpedizione.setVisible(stato);
	}
	
	public void mostraLblVuoto(){
        JLabel lblVuoto = new JLabel("Il tuo carrello \u00E8 vuoto!");
    	lblVuoto.setFont(new Font("Arial", Font.ITALIC, 17)); 
    	lblVuoto.setBounds(200, 6, 344, 49);
    	pannelloCheckout.add(lblVuoto);
	}

	public void impostaPesoTot(String str) {
		lblPesoTot.setText(str);
	}

	public String getValTextFieldMappa(int indexArray) {
		return mapTextfield.get(indexArray).getText();
	}
	
	public void pulisciCarrello(){
		mapTextfield.clear();
		pannelloCheckout.removeAll();
	}
	
	public void ricaricaCarrello(){
		pannelloCheckout.revalidate();
		pannelloCheckout.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
		repaint();
		revalidate();
	}
	
	public void cambiaPannello(int tipo){
		// TIPO 1 = pannelloCheckout
		// TIPO 2 = pannelloSpedizione
		// TIPO 3 = pannelloPagamento
		removeAll();
		if(tipo == 1){
			creaHeader();
			creaPanCheckout();
		}else if(tipo == 2){
			creaPanSpedizione();
			setPreferredSize(new Dimension(660, 305));
		}else if(tipo == 3){
			creaPanPagamento();
			setPreferredSize(new Dimension(513, 285));
		}
		repaint();
		revalidate();
	}

	public void impostaLblSubTotale(String str) {
		lblSubTotale.setText(str);
	}

	public void impostaLblSpedizTratt(String str) {
		lblSpedizioneTratt.setText(str);
	}
	
	public void impostaTxtSubTotale(String str){
		lblPrezzoSub.setText(str);
	}

	public void impostaTxtPrezzoSpedizione(String str) {
		lblPrezzoSpedizione.setText(str);
	}
	
	public float getSpeseSpedizione(){
		return speseSpedizione;
	}

	public void impostaSpedizionePrezzoTot(String str) {
		lblPrezzoTot.setText(str);
	}
	
	public void cambioInfoSpedizione(boolean stato){
		icoInfo.setVisible(stato);
	}
	
	public String getCorriere(){
		return tipoCorriere;
	}
	
	public void setCorriere(float spese, String tipo){
		speseSpedizione = spese;
		tipoCorriere = tipo;
	}

	public void setUtenteSpeciale(boolean stato) {
		utenteSpeciale = stato;		
	}
	
	public boolean isUtenteSpeciale(){
		return utenteSpeciale;
	}

	public String getTxtIndirizzo() {
		return txtIndirizzo.getText();
	}

	public String getTxtNIndirizzo() {
		return txtNIndirizzo.getText();
	}

	public String getTxtCitta() {
		return txtCitta.getText();
	}

	public String getTxtCap() {
		return txtCap.getText();
	}
	
	public void setIcoSpedizione(int tipo, ImageIcon ico){
		switch(tipo){
			case 1:
				icoIndirizzo.setIcon(ico);
				break;
			case 2:
				icoCitta.setIcon(ico);
				break;
			case 3:
				icoCap.setIcon(ico);
				break;
		}
	}
	
	public void setIndirizzo(String indirizzo, String citta, String civico, String cap){
		strIndirizzo = indirizzo;
		strCitta = citta;
		strCivico = civico;
		strCap = cap;
	}
	
	public String getIndirizzoConCivico(){
		return strIndirizzo + ", " + strCivico;
	}
	
	public String getCittaConCap(){
		return strCitta+" ("+strCap+")";
	}
	
	public String getTipoPagamento(){
		return tipoPagamento;
	}
	
	public void setTipoPagamento(String str){
		tipoPagamento = str;
	}
	
	public void setTotaleBonifico(String str){
		lblTotaleBonifico.setText(str);
	}
	
	public String getIntestarioCarta(){
		return txtIntestario.getText();
	}
	
	public String getCodiceCartaUno(){
		return txtCartaUno.getText();
	}
	
	public String getCodiceCartaDue(){
		return txtCartaDue.getText();
	}
	
	public String getCodiceCartaTre(){
		return txtCartaTre.getText();
	}
	
	public String getCodiceCartaQuattro(){
		return txtCartaQuattro.getText();
	}
	
	public String getCodiceCartaCVV(){
		return txtCVV.getText();
	}
	
	public String getRicevutaBonifico(){
		return txtRicevutaBonifico.getText();
	}
	
	public void setIcoPagamento(int tipo, ImageIcon ico){
		switch(tipo){
			case 1:
				icoVIntestario.setIcon(ico);
				break;
			case 2:
				icoVCarta.setIcon(ico);
				break;
			case 3:
				icoVCVV.setIcon(ico);
				break;
			case 4:
				icoRicevutaBonifico.setIcon(ico);
				break;
		}
	}
	
	public void disabilitaPagamento(int tipo){
		if(tipo == 2){
			txtCartaUno.setEnabled(false);
			txtCartaDue.setEnabled(false);
			txtCartaTre.setEnabled(false);
			txtCartaQuattro.setEnabled(false);
			txtIntestario.setEnabled(false);
			txtCVV.setEnabled(false);
		}else if(tipo == 3){
			txtRicevutaBonifico.setEnabled(false);
		}
		
        icoVerificaPagamento.setVisible(true);
        lblVerificaPagamento.setVisible(true);
        btnConfermaPagamento.setEnabled(false);
        btnTornaSpedizione.setEnabled(false);
        radioPayPal.setEnabled(false);
        radioCartaCredito.setEnabled(false);
        radioBonifico.setEnabled(false);
	}
}