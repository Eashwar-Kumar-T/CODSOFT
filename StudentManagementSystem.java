package project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
class Student{
		protected String name;
		protected int rollno;
		protected int grade;
		protected String dept;
}
public class StudentManagementSystem extends Student{
		private ArrayList<Student> students = new ArrayList<Student>();
		int addstudent(String n,int r,int g,String d) {
			Student obj = new Student();
			obj.name=n;
			obj.rollno=r;
			obj.grade=g;
			obj.dept=d;
			students.add(obj);
			return 1;
		}
		Student removestudent(String n) {
			Student obj = new Student();
			int c=0;
			for(int i=0;i<students.size();i++) {
				obj = students.get(i);
				if ((obj.name).equals(n)) {
					c=1;
					students.remove(i);
					return obj;
				}
			}
			return null;
		}
		Student searchstudent(String n) {
			Student obj = new Student();
			int c=0;
			for(int i=0;i<students.size();i++) {
				obj=students.get(i);
				if((obj.name).equals(n)) {
					c=1;
					return obj;
				}
			}
			if(c==0) {
				System.out.println("Student not found!");
			}
			return null;
		}
		ArrayList<Student> displaystudents() {
			if (students.size()==0) {
				return null;
			}
			return students;
		}
	    public static void main(String []args) {
	    	StudentManagementSystem SMS = new StudentManagementSystem();
		    new gui(SMS);
	   }
}
class gui{
	private static int count;
	private JLabel label,label1;
	private JPanel panel;
	private static JFrame frame1;
	private JFrame frame;
	private static int isadded;
	static int currentIndex = 0;
	public gui(StudentManagementSystem obj) {
		frame = new JFrame();
		label = new JLabel("WELCOME TO STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
	    label1 =  new JLabel("\n");
		JButton addButton = new JButton( new AbstractAction("ADD A NEW RECORD") {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		            add(panel,frame,obj);
		        }
		    });

		JButton substractButton = new JButton( new AbstractAction("REMOVE A RECORD") { 
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		            delete(panel,frame,obj);
		        }
		    });
		JButton divdeButton = new JButton( new AbstractAction("SEARCH FOR A STUDENT") { 
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	               search(panel,frame,obj);
	        }
	    });
		JButton multiplyButton = new JButton( new AbstractAction("DISPLAY ALL STUDENTS") { 
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	               display(panel,frame, obj);
	        }
	    });
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(label1);
		panel.add(addButton);
		panel.add(substractButton);
		panel.add(divdeButton);
		panel.add(multiplyButton);
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setVisible(true);
		
	}

	 static void add(JPanel panel, JFrame frame, StudentManagementSystem obj) {
	        panel.removeAll();
	        JLabel label2 = new JLabel("ADDING RECORDS", SwingConstants.CENTER);
	        JLabel label3 = new JLabel("Enter name : ");
	        JTextField t1 = new JTextField();
	        JLabel label4 = new JLabel("Enter roll no. : ");
	        JTextField t2 = new JTextField();
	        JLabel label5 = new JLabel("Enter department : ");
	        JTextField t3 = new JTextField();
	        JLabel label6 = new JLabel("Enter grade : ");
	        JTextField t4 = new JTextField();

	        JLabel label7 = new JLabel();

	        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
	        panel.setLayout(new GridLayout(0, 1));
	        panel.add(label2);
	        panel.add(label3);
	        panel.add(t1);
	        panel.add(label4);
	        panel.add(t2);
	        panel.add(label5);
	        panel.add(t3);
	        panel.add(label6);
	        panel.add(t4);

	        JButton addButton = new JButton("ADD STUDENT");
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String name = t1.getText();
	                int roll = 0;
	                int grade = 0;
	                roll = Integer.parseInt(t2.getText());
	                grade = Integer.parseInt(t4.getText());
	                String department = t3.getText();

	                int i = obj.addstudent(name, roll, grade, department);
	                if (i == 1) {
	                    label7.setText("Student successfully added!");
	                    panel.add(label7);
	                    frame.revalidate();
	                    frame.repaint();

	                    Timer timer = new Timer(2000, new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            panel.remove(label7);
	                            frame.revalidate();
	                            frame.repaint();
	                        }
	                    });
	                    timer.setRepeats(false); 
	                    timer.start(); 
	                }
	            }
	        });

	        JButton backButton = new JButton("BACK TO MENU");
	        backButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                frame.setVisible(false);
	                new gui(obj);
	            }
	        });
	        panel.add(addButton);
	        panel.add(backButton);
	        frame.add(panel, BorderLayout.CENTER);
	        frame.setSize(new Dimension(600,600));
	        frame.setVisible(true);
	    }

	static void delete(JPanel panel,JFrame frame,StudentManagementSystem obj) {
		panel.removeAll();
	    JLabel label2 = new JLabel("DELETE RECORDS",SwingConstants.CENTER);
		JLabel label3 = new JLabel("Enter name to remove : ");
		JTextField t1 = new JTextField();
        isadded = 0;
		JButton submit = new JButton( new AbstractAction("SUBMIT") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	String searchName = t1.getText();
	        	JLabel label4;
	            Student curr;
	            curr = obj.removestudent(searchName);
	            if(isadded==1) {
	            	panel.remove(panel.getComponentCount()-1);
	            }
	            else if(isadded==2) {
	            	panel.remove(panel.getComponentCount()-5);
	            	panel.remove(panel.getComponentCount()-4);
	            	panel.remove(panel.getComponentCount()-3);
	            	panel.remove(panel.getComponentCount()-2);
	            	panel.remove(panel.getComponentCount()-1);
	            }
	            if(curr==null) {
	            	label4= new JLabel("Student not in records",SwingConstants.CENTER);
	            	panel.add(label4);
	            	
	            	frame.setSize(600, 450);
	        		frame.add(panel,BorderLayout.CENTER);
	        		frame.setVisible(true);
	        		isadded = 1;
	            }
	            else {
	            	
	              isadded = 1;
	              JLabel label5, label6, label7, label8;
	              label4= new JLabel("Student Record deleted!",SwingConstants.CENTER);
	              label5 = new JLabel("Student name : "+curr.name,SwingConstants.CENTER);
	              label6 = new JLabel("Student rollno. : "+curr.rollno,SwingConstants.CENTER);
	              label7 = new JLabel("Student Department : "+curr.dept,SwingConstants.CENTER);
	              label8 = new JLabel("Student grade : "+curr.grade,SwingConstants.CENTER);
	            
                  panel.add(label4);
                  panel.add(label5);
                  panel.add(label6);
                  panel.add(label7);
                  panel.add(label8);
                  
                  isadded = 2;
                  
                  frame.setSize(600, 500);
        		  frame.add(panel,BorderLayout.CENTER);
        		  frame.setVisible(true);
        		  
	            }
	        }
	    });
		 JButton backButton = new JButton("BACK TO MENU");
		    backButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	frame.setVisible(false);
		            new gui(obj);
		        }
		    });
		
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label2);
		panel.add(label3);
		panel.add(t1);
		panel.add(submit);
		panel.add(backButton);
		
		frame.setSize(600, 400);
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	static void search(JPanel panel,JFrame frame,StudentManagementSystem obj) {
		panel.removeAll();
	    JLabel label2 = new JLabel("SEARCH RECORDS",SwingConstants.CENTER);
		JLabel label3 = new JLabel("Enter name to search : ");
		JTextField t1 = new JTextField();
		isadded = 0;
		JButton submit = new JButton( new AbstractAction("SUBMIT") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	String searchName = t1.getText();
	        	JLabel label4;
	            Student curr;
	            curr = obj.searchstudent(searchName);
	            if(isadded==1) {
	            	panel.remove(panel.getComponentCount()-1);
	            }
	            else if(isadded==2) {
	            	panel.remove(panel.getComponentCount()-5);
	            	panel.remove(panel.getComponentCount()-4);
	            	panel.remove(panel.getComponentCount()-3);
	            	panel.remove(panel.getComponentCount()-2);
	            	panel.remove(panel.getComponentCount()-1);
	            }
	            if(curr==null) {
	            	label4= new JLabel("Student not in records",SwingConstants.CENTER);
	            	panel.add(label4);
	            	
	            	isadded =1;
	            	frame.setSize(600, 450);
	        		frame.add(panel,BorderLayout.CENTER);
	        		frame.setVisible(true);
	            }
	            else {
	              label4= new JLabel("Student found!",SwingConstants.CENTER);
	              JLabel label5 = new JLabel("Student name : "+curr.name,SwingConstants.CENTER);
	              JLabel label6 = new JLabel("Student rollno. : "+curr.rollno,SwingConstants.CENTER);
	              JLabel label7 = new JLabel("Student Department : "+curr.dept,SwingConstants.CENTER);
	              JLabel label8 = new JLabel("Student grade : "+curr.grade,SwingConstants.CENTER);
	            
                  panel.add(label4);
                  panel.add(label5);
                  panel.add(label6);
                  panel.add(label7);
                  panel.add(label8);
                  
                  isadded = 2;
                frame.setSize(600, 500);
        		frame.add(panel,BorderLayout.CENTER);
        		frame.setVisible(true);
	            }
	        }
	    });
		JButton backButton = new JButton("BACK TO MENU");
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	frame.setVisible(false);
	            new gui(obj);
	        }
	    });
		
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label2);
		panel.add(label3);
		panel.add(t1);
		panel.add(submit);
		panel.add(backButton);
		
		frame.setSize(600, 400);
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	static void display(JPanel panel, JFrame frame, StudentManagementSystem obj) {
	    panel.removeAll();
	    JLabel label2 = new JLabel("DISPLAY RECORDS", SwingConstants.CENTER);
	    panel.add(label2);
	    panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
	    panel.setLayout(new GridLayout(0, 1));

	    ArrayList<Student> ord = obj.displaystudents();
		if (ord == null || ord.isEmpty() || currentIndex>=ord.size()) {
	        JLabel label4 = new JLabel("No more records found!", SwingConstants.CENTER);
	        panel.add(label4);
		    frame.add(panel, BorderLayout.CENTER);
		    frame.setSize(600, 350);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
	    } else {
	        Student student = ord.get(currentIndex);
	        JLabel label5 = new JLabel("Student name : " + student.name, SwingConstants.CENTER);
	        JLabel label6 = new JLabel("Student rollno. : " + student.rollno, SwingConstants.CENTER);
	        JLabel label7 = new JLabel("Student Department : " + student.dept, SwingConstants.CENTER);
	        JLabel label8 = new JLabel("Student grade : " + student.grade, SwingConstants.CENTER);

	        JButton next = new JButton(new AbstractAction("NEXT") {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                  if(currentIndex<ord.size()-1) {
	                	  currentIndex++;
	                      display(panel, frame, obj);
	                  }
	            }
	        });
	        
	        JButton Prev = new JButton(new AbstractAction("PREVIOUS") {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                  if(currentIndex>0) {
	                	  currentIndex--;
	                      display(panel, frame, obj);
	                  }
	            }
	        });
	        
	        panel.add(label5);
	        panel.add(label6);
	        panel.add(label7);
	        panel.add(label8);
	        panel.add(next);
	        panel.add(Prev);
	        
	        frame.setSize(600, 550);
	    }
		JButton backButton = new JButton("BACK TO MENU");
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	frame.setVisible(false);
	            new gui(obj);
	        }
	    });
		panel.add(backButton);
	    frame.getContentPane().removeAll();
	    frame.add(panel, BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}
