
import java.math.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Calculator  implements ActionListener {
	
	static JFrame frame;
	static JTextField textField3,textField2,textField1,textField4,textField5,textField6; // added third, unseen textField3.... consider JtextArea or text pane???
	static JButton[] numberButtons = new JButton[10];
	static JButton[] functionButtons = new JButton[10];
	
	static JButton addButton,subButton,multiButton,diviButton,decimalButton;
	static JButton equalButton,deleteButton,clearButton,squareRootButton,percentButton;
	
	static JPanel panel10;
	static JLabel label;
	
	static Font myFont = new Font("Monospaced", Font.BOLD,34); //text field-2
	static Font font1 = new Font("Monospaced", Font.BOLD, 30); // label
	static Font font2 = new Font("DialogInput", Font.ITALIC, 30); //text field-3 
	static Font font3 = new Font("SansSerif", Font.BOLD, 39); // result display field3 equalsButton press
	static Font font4 = new Font("DialogInput", Font.BOLD, 40); // equalButton
	static Font font5 = new Font("Monospaced", Font.BOLD,20); // numberButtons
	static Font font6 = new Font("Monospaced", Font.ITALIC,30); // functionButtons+-*/
	static Font font7 = new Font("Dialog", Font.BOLD,22); // percentButton
	static Font font8 = new Font("Serif", Font.BOLD,24); // AC clear button
	
	double num1=0,num2=0;
	 double result=0;
	char operator;
	


	public static final Color blue_gray = new Color(46, 56, 58); // equalButton
	public static final Color BLACK1_COLOR = new Color(10, 15, 15); // number Buttons inner square
	public static final Color BLACK2_COLOR = new Color(13, 16, 18); // textFields and Label
	public static final Color BLACK3_COLOR = new Color(25, 30, 30); // outer square function buttons

		public static void main(String[] args) {
				
		Calculator calculator = new Calculator();	// initialized object after public static void main() method  and using it as object fixed this keyword issue
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(190, 59, 316, 522);
		frame.setResizable(true);
		frame.setOpacity(1);
		frame.setVisible(true);	
		
		Border border2 = new LineBorder(BLACK2_COLOR, 2, false);
		label = new JLabel();
		label.setBounds(0,0, 300, 300);
		label.setVisible(true);
		label.setText("");
		label.setForeground(Color.blue);
		label.setBackground(BLACK2_COLOR);
		label.setFont(new Font("CONSOLE", 2, 15));
		label.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		label.setBorder(border2);
		label.setOpaque(true);
		
		textField3 = new JTextField(100); // is top most field to show work. .concat +-*/... is visible
		textField3.setFont(font2);
		textField3.setBackground(BLACK2_COLOR);
		textField3.setBorder(border2);
		textField3.setOpaque(true);
		textField3.setHorizontalAlignment(JTextField.RIGHT);
		textField3.setFocusable(false);
		
		textField2 = new JTextField(100); //displays results 2nd down from top.. is visible
		textField2.setFont(myFont);
		textField2.setBackground(BLACK2_COLOR);
		textField2.setBorder(border2);
		textField2.setVisible(true);
		textField2.setOpaque(true);
		textField2.setHorizontalAlignment(JTextField.RIGHT);
		textField2.setFocusable(false);
		
		textField1 = new JTextField(); // textField1 is 3rd from bottom invisible. parsing occuring mainly here
		textField1.setBounds(1,510, 300, 35); 
		textField1.setFont(myFont);
		textField1.setVisible(true);
	
		textField4 = new JTextField(); // invisible bottom most field allows for instant parsing and change of result into double digits
		textField4.setBounds(1,549, 300, 35); 
		textField4.setFont(myFont);
		textField4.setVisible(true);
		
		textField5 = new JTextField(); // invisible bottom most field allows for instant parsing and change of result into double digits
		textField5.setBounds(1,580, 300, 35); 
		textField5.setFont(myFont);
		textField5.setVisible(true);
		
		textField6 = new JTextField(); // invisible bottom most field allows for instant parsing and change of result into double digits
		textField6.setBounds(1,630, 300, 35); 
		textField6.setFont(myFont);
		textField6.setVisible(true);
			
		addButton = new JButton("+");
		subButton = new JButton("-");
		multiButton = new JButton("x");
		diviButton = new JButton("÷");
		decimalButton = new JButton(".");
		equalButton = new JButton("=");
		deleteButton = new JButton("←");
		clearButton = new JButton("AC");
		squareRootButton = new JButton("√");
		percentButton = new JButton("%");
		
		functionButtons[0] = percentButton;
		functionButtons[1] = clearButton;
		functionButtons[2] = deleteButton;
		functionButtons[3] = diviButton;
		functionButtons[4] = multiButton;
		functionButtons[5] = subButton;
		functionButtons[6] = addButton; 
		functionButtons[7] = squareRootButton;
		functionButtons[8] = decimalButton;
		functionButtons[9] = equalButton;
				
		for(int i=0; i<10; i++) {
			functionButtons[i].addActionListener(calculator); // calculator used instead of this as all is static
			functionButtons[i].setFont(font6);
			functionButtons[i].setFocusable(false);
			functionButtons[i].setBorder(new SoftBevelBorder(BevelBorder.RAISED));	
			//functionButtons[i].setBorder(new RoundButton(33));
		}	
		for(int i=0; i<10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(calculator); 
			numberButtons[i].setFont(font5);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setBorder(new SoftBevelBorder(BevelBorder.RAISED));
			//numberButtons[i].setBorder(new RoundButton(33));
		}
	
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		panel1.setBackground(BLACK2_COLOR);
		panel2.setBackground(Color.green);
		panel3.setBackground(Color.yellow);
		panel4.setBackground(Color.magenta);
		panel5.setBackground(Color.blue);
		
		panel5.setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));	
		
		panel1.setPreferredSize(new Dimension(200,200));
		panel2.setPreferredSize(new Dimension(20,20));
		panel3.setPreferredSize(new Dimension(20,20));
		panel4.setPreferredSize(new Dimension(20,20));
		panel5.setPreferredSize(new Dimension(200,500));
		
		//JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel(new GridLayout(5,4,0,0)); 
		
		/////////////////////////// Row 1
		panel10.add(deleteButton);
		panel10.add(clearButton);
		panel10.add(percentButton);
		panel10.add(diviButton);   // Outer **
		
		//////////////////////////// Row 2
		panel10.add(numberButtons[7]);
		panel10.add(numberButtons[8]);
		panel10.add(numberButtons[9]);
		panel10.add(multiButton);  // Outer **
		
		//////////////////////////// Row 3
		panel10.add(numberButtons[4]);
		panel10.add(numberButtons[5]);
		panel10.add(numberButtons[6]);
		panel10.add(subButton);     // Outer **
		
		//////////////////////////// Row 4
		panel10.add(numberButtons[1]);
		panel10.add(numberButtons[2]);
		panel10.add(numberButtons[3]);
		panel10.add(addButton);      // Outer **
		
		//////////////////////////// Row 5
		panel10.add(squareRootButton);
		panel10.add(numberButtons[0]);
		panel10.add(decimalButton);
		panel10.add(equalButton);    // Outer **
		
		panel1.add(label);
		panel1.add(textField3);
		panel1.add(textField2);
		
		//panel6.setBackground(Color.blue);
		panel7.setBackground(Color.darkGray);
		panel8.setBackground(BLACK2_COLOR);
		panel9.setBackground(BLACK2_COLOR);
		panel10.setBackground(Color.white);
	
		panel5.setLayout(new BorderLayout(0,0)); //main panel5 set borderlayout first
				
		//panel6.setPreferredSize(new Dimension(50,100)); // setPrefferedSize integral to nest working
		//panel7.setPreferredSize(new Dimension(100,70));
		//panel8.setPreferredSize(new Dimension(50,50));
		panel9.setPreferredSize(new Dimension(00,50)); // effects above and right side black space.. size not adjustable.
		panel10.setPreferredSize(new Dimension(550,550));
	
		//panel5.add(panel8,BorderLayout.WEST);
		panel5.add(panel9,BorderLayout.EAST);
		
		panel5.add(panel10,BorderLayout.CENTER);

		
		frame.add(textField1); // CALCULATIONS
		frame.add(textField4); // CALCULATIONS
		frame.add(textField5); // extra for me to visualize 
		frame.add(textField6);
		
		frame.add(panel1,BorderLayout.NORTH); // must be last to add to show
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));	
		
		frame.add(panel5,BorderLayout.CENTER);
	
		for(int i=0; i<10; i++) {
		    numberButtons[i].setBackground(BLACK1_COLOR);
		    numberButtons[i].setForeground(Color.white);
		}	
		
		addButton.setForeground(Color.blue);
		subButton.setForeground(Color.blue);
		multiButton.setForeground(Color.blue);
		diviButton.setForeground(Color.blue);
		percentButton.setForeground(Color.blue);

		percentButton.setBackground(Color.DARK_GRAY);
		addButton.setBackground(Color.darkGray);
		subButton.setBackground(Color.darkGray);
		multiButton.setBackground(Color.darkGray);
		diviButton.setBackground(Color.darkGray);
		
		for(int i=0; i<10; i++) {
		    functionButtons[i].setForeground(Color.white);
		    functionButtons[i].setBackground(BLACK3_COLOR);
		}
		
		decimalButton.setForeground(Color.white);
		decimalButton.setBackground(BLACK1_COLOR);
		equalButton.setForeground(Color.WHITE);
		equalButton.setBackground(Color.darkGray);
		squareRootButton.setForeground(Color.white);
		squareRootButton.setBackground(BLACK1_COLOR);
		equalButton.setForeground(Color.WHITE);
		equalButton.setBackground(blue_gray);
		
		squareRootButton.setFont(font5);	
		deleteButton.setFont(font2); 
		equalButton.setFont(font4);
		percentButton.setFont(font7);
		clearButton.setFont(font8);
		decimalButton.setFont(font1);
	}	
		
	public  void actionPerformed(ActionEvent e) {
		
	 DecimalFormat decimalFormat = new DecimalFormat("#.##########"); //DF class if ( 0.##) not set auto strips extra .000s
		for(int i=0; i<10; i++) { // for loop under actionPerformed Class
			if(e.getSource() == numberButtons[i]) { //nested if(){ statement inside for(){  loop
				
				textField1.setText(textField1.getText().concat(String.valueOf((decimalFormat.format(i)))));// argument to fill field with i number button
				textField3.setText(textField3.getText().concat(String.valueOf(decimalFormat.format((i)))));	
				textField4.setText(textField4.getText().concat(String.valueOf(((i)))));	
			
				textField5.setText(textField5.getText().concat(String.valueOf(decimalFormat.format((i))))); 
			
				textField3.setFont(font2);
				textField3.setForeground(Color.white);
				textField3.setBackground(BLACK2_COLOR);
				textField2.setForeground(Color.white);
				textField2.setBackground(BLACK2_COLOR);
				textField2.setHorizontalAlignment(JTextField.RIGHT);
	
			}
		}
			if (e.getSource()==decimalButton) {// Decimal Button  0.0.0.0.0.0.0.0
				textField1.setText(textField1.getText().concat("."));
				textField3.setText(textField3.getText().concat("."));
				textField4.setText(textField4.getText().concat("."));
	   	 }	
			if (e.getSource()==multiButton) {// Multiplication Button 0*0*0*0*0			
				num1 = Double.parseDouble(textField1.getText());
				operator = '*';
				textField3.setText(textField3.getText().concat("x"));
				textField4.setText("");
				textField4.setText(textField4.getText().concat(""));	
		}
			if (e.getSource()==diviButton) { // division 
				num1 = Double.parseDouble(textField1.getText());
				operator = '/';
				textField3.setText(textField3.getText().concat("÷"));
				textField4.setText("");
				textField4.setText(textField4.getText().concat(""));
		}	
			if (e.getSource()==subButton) {	// SUBTRACTION bUTTON -1-1-1-1-1-
			
					textField1.setText(String.valueOf(result));
				//  2 negatives make a positive.
					if (result>0.00) {
						
						operator='-';
						num1=(Double.parseDouble(textField1.getText()));
						textField5.setText(String.valueOf(result));
					}
					
				//textField1.setText(String.valueOf(result));
				if ( operator=='*' || operator=='/'  ) { // changes operator to '*'||'/' when multiplying negative numbers.
					// fix when negative result minus operator stuck on '*' or '/' ????
						if (result==num1*-num2) {
								result*= -1;
								num1=(Double.parseDouble(textField1.getText()));
								textField1.setText(String.valueOf(result));
							}
		
					textField1.setText(String.valueOf(result));
					textField3.setText(textField3.getText().concat("-"));
					textField4.setText("");
					textField4.setText(textField4.getText().concat(""));
					
						if (num1<0.00  ) {
							
							num1=Math.abs(Double.parseDouble(textField1.getText()));
							textField1.setText(String.valueOf(result));	
						
						}
						
						
				}
			
				else {
				
					num1=Double.parseDouble(textField1.getText());
					operator='-';
					textField3.setText(textField3.getText().concat("-"));
					textField4.setText("");
					textField4.setText(textField4.getText().concat(""));	
				}
				
				////*
				if (textField6.getText().isEmpty() ) { // checks is extra textField '6' is empty 
					textField1.setText(String.valueOf(result));	
					num1=Double.parseDouble(textField5.getText()); // puts num1 in different field for subtraction
				}
				if (operator=='-') {
					textField6.setText(String.valueOf(num2));
				}
				else {
					textField6.setText("");
				}
				////*
		 }
			
			if (e.getSource()==addButton) { // Addition Button  +0+0+0+0+
				num1 = Double.parseDouble(textField1.getText());
				operator = '+';
				textField3.setText(textField3.getText().concat("+"));
				textField4.setText("");
				textField4.setText(textField4.getText().concat(""));
		 }

			if (e.getSource()==percentButton) { // division 
				num1 = Double.parseDouble(textField1.getText());
				operator = '%';
				textField3.setText(textField3.getText().concat("%"));
				textField4.setText("");
				textField4.setText(textField4.getText().concat(""));
				textField2.setText(String.valueOf(decimalFormat.format((num1/100))));
				textField1.setText(textField2.getText());
		}	
			if (e.getSource()==squareRootButton) {
				
					if (result==0) {
					textField1.setText(String.valueOf(result));	// 
				}
					
				num1 = Double.parseDouble(textField1.getText());
				operator = '√';
				textField3.setText(textField3.getText().concat("√"));
				textField4.setText("");
				textField4.setText(textField4.getText().concat("")); 
			}
			if (e.getSource()==equalButton) {	
					var dfs = new DecimalFormatSymbols();
					dfs.setGroupingSeparator(',');
					var df = new DecimalFormat("#,##0.######",dfs);
				num2=Double.parseDouble(textField4.getText());
				textField2.setText("");
				textField1.setText(""); 
				textField2.setText(String.valueOf(df.format(result)));
				textField1.setText(String.valueOf(result)); 
				textField4.setText(textField2.getText()); 
				
				//**hides field2 after equalButton press**
				textField2.setVisible(false);
				textField3.setFont(font3);
				textField3.setText("");
				
					textField3.setText(String.valueOf(df.format(result)));
					textField3.setForeground(Color.white);
					textField3.setBackground(BLACK2_COLOR);
					textField2.setHorizontalAlignment(JTextField.RIGHT);
					textField3.setHorizontalAlignment(JTextField.RIGHT);	
		}
	
			for(int i=0; i<10; i++) { // for loop under actionPerformed Class
				if(e.getSource()==numberButtons[i]) {
					
					num2=Double.parseDouble(textField4.getText());
					textField2.setVisible(true);
					textField3.setBackground(BLACK2_COLOR);
					textField3.setFont(font2);
					textField3.setHorizontalAlignment(JTextField.RIGHT);
					textField2.setHorizontalAlignment(JTextField.RIGHT);
					textField2.setBackground(BLACK2_COLOR);
				
			
					
				switch(operator) {
					
					case'*':
						result=num1*num2;	
						textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
						
						break;
					case'/':
						result=num1/num2; 
						textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
						break;		
					case'-':	
					
						result=num1+(-1*num2);
						textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
						break;
					case'+':		
						result=num1+num2;		
						textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
						break;
					case'%':  
					    result=(num1/100)*num2; 
					   	textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
					case'√':
						result=(Math.sqrt(num2));
						textField1.setText(String.valueOf(decimalFormat.format(result)));
						textField5.setText(String.valueOf(decimalFormat.format(num1)));	
					}
				}
			// fixes decimal format display floating point error and commas for thousands
				DecimalFormat numform = new DecimalFormat("###,##0.###########");
				textField2.setText(String.valueOf(numform.format(result)));		
		}	
			
		if (e.getSource()==clearButton) {	
			textField3.setText("");
			textField2.setText("");
			textField1.setText("");
			textField4.setText("");
			textField5.setText("");
			num1=0;
			num2=0;
			result=0;
			operator='\0';	
			//**reveals field2 after numberButtons press**	// cut?
			//textField2.setBounds(1, 91, 300, 42); // resets textfield3 and font back to normal with numberbuttons
			//textField3.setBounds(1,55, 300, 47);
			textField3.setFont(font2);
			textField3.setBackground(BLACK2_COLOR);
			textField2.setForeground(Color.white);
			textField2.setBackground(BLACK2_COLOR);
			textField2.setVisible(true);	
			/////////////////////////////////////// cut?
		}	
	
		if(e.getSource()==deleteButton) {
		   String string = textField3.getText();
		   DecimalFormat numform = new DecimalFormat("###,##0.###########");
			textField2.setText(String.valueOf(numform.format(result)));	
		  
		   	num1=Double.parseDouble(textField5.getText());
		  	num2=Double.parseDouble(textField4.getText());
	
		   
		   	   
		   	textField1.setText(textField1.getText().substring(0, textField1.getText().length() - 1));
		   	
		   	textField2.setText(textField2.getText().substring(0, textField2.getText().length() - 1));
		   	
		   	textField3.setText(textField3.getText().substring(0, textField1.getText().length() - 1));
		   	
		   	textField4.setText(textField4.getText().substring(0, textField1.getText().length() - 1));
		   	
		   	textField5.setText(textField5.getText().substring(0, textField1.getText().length() - 1));
		   	
		
		   	
	    	
		   
	   }	
	}	
}
