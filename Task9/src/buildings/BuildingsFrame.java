package buildings;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class BuildingsFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBuilding building;
	private JPanel planPanel;
	private JScrollPane scrollPane;
	private JLabel 		buildingType
					,	floorsCount
					,	totalAreaBuilding
					,	floorNumber
					,	spacesCount
					,	totalAreaFloor
					,	spaceNumber
					,	roomsCount
					,	totalAreaSpace;
	
	private JMenu lookAndFeelMenu;
	private JRadioButton[] radioButtons;
	private LookAndFeelInfo[] lafi;
	
	public BuildingsFrame()
	{
		setTitle("Task 8");
		setSize(500, 420);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		});
		
		fileMenu.add(exitMenuItem);
		
		lookAndFeelMenu = new JMenu("Look&Feel");
		lafi = UIManager.getInstalledLookAndFeels();
		radioButtons = new JRadioButton[lafi.length];
		ActionListener radioButtonActionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JRadioButton selectedRadioButton = (JRadioButton)ae.getSource();
				for ( int b = 0; b < radioButtons.length; b++)
				{
					radioButtons[b].setSelected(false);	
				}
				selectedRadioButton.setSelected(true);
				
				if ( selectedRadioButton.isSelected())
				{
					try {
						
						UIManager.setLookAndFeel(lafi[lookAndFeelMenu.getPopupMenu().getComponentIndex(selectedRadioButton)].getClassName());
						SwingUtilities.updateComponentTreeUI(rootPane);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lookAndFeelMenu.getPopupMenu().setVisible(false);
					repaint();
					
				}
				
				
			}
		};
		
		for ( int e = 0; e<lafi.length; e++)
		{
			radioButtons[e] = new JRadioButton(lafi[e].getName());
			radioButtons[e].addActionListener(radioButtonActionListener);
			lookAndFeelMenu.add(radioButtons[e]);
		}
		radioButtons[0].setSelected(true);
		
		
		menuBar.add(fileMenu);
		menuBar.add(lookAndFeelMenu);
		
		//UIManager.getInstalledLookAndFeels()[0].getName();
		
		setJMenuBar(menuBar);
		
		//окно
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JPanel buildingPanel = new JPanel();
		JPanel floorPanel = new JPanel();
		JPanel spacePanel = new JPanel();
		planPanel = new JPanel();
		scrollPane = new JScrollPane(planPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		planPanel.setLayout(new BoxLayout(planPanel, BoxLayout.Y_AXIS));
				
		// ^как задать всем компонентам одинаковый, но не фиксированный размер?
		buildingPanel.setMaximumSize(new Dimension(140,120));
		floorPanel.setMaximumSize(new Dimension(140,120));
		spacePanel.setMaximumSize(new Dimension(140,120));
		planPanel.setMaximumSize(new Dimension(350, 400));
		// $как задать всем компонентам одинаковый, но не фиксированный размер?
		
		buildingPanel.setBorder(BorderFactory.createTitledBorder("Building"));
		floorPanel.setBorder(BorderFactory.createTitledBorder("Floor"));
		spacePanel.setBorder(BorderFactory.createTitledBorder("Space"));
		planPanel.setBorder(BorderFactory.createEtchedBorder());
		
		buildingPanel.setLayout(new BoxLayout(buildingPanel, BoxLayout.Y_AXIS));
		floorPanel.setLayout(new BoxLayout(floorPanel, BoxLayout.Y_AXIS));
		spacePanel.setLayout(new BoxLayout(spacePanel, BoxLayout.Y_AXIS));
		
		buildingPanel.add(new JLabel("Building type"));
		buildingType = new JLabel("Unknow");
		buildingPanel.add(buildingType);
				
		buildingPanel.add(new JLabel("Count of floors"));
		floorsCount = new JLabel("0");
		buildingPanel.add(floorsCount);
		buildingPanel.add(new JLabel("Total area"));
		totalAreaBuilding = new JLabel("0");
		buildingPanel.add(totalAreaBuilding);
		
		floorPanel.add(new JLabel("Floor Number"));
		floorNumber = new JLabel("0");
		floorPanel.add(floorNumber);
		floorPanel.add(new JLabel("Count of spaces"));
		spacesCount = new JLabel("0");
		floorPanel.add(spacesCount);
		floorPanel.add(new JLabel("Total area"));
		totalAreaFloor = new JLabel("0");
		floorPanel.add(totalAreaFloor);
		
		spacePanel.add(new JLabel("Space Number"));
		spaceNumber = new JLabel("0");
		spacePanel.add(spaceNumber);
		spacePanel.add(new JLabel("Count of rooms"));
		roomsCount = new JLabel("0");
		spacePanel.add(roomsCount);
		spacePanel.add(new JLabel("Total area"));
		totalAreaSpace = new JLabel("0");
		spacePanel.add(totalAreaSpace);
		
		leftPanel.add(buildingPanel);
		leftPanel.add(floorPanel);
		leftPanel.add(spacePanel);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		//mainPanel.add(planPanel, BorderLayout.CENTER);
		mainPanel.add(scrollPane);
		
		openMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION)
				{
					try {
						building = Buildings.readBuilding(new FileReader(fileopen.getSelectedFile()));
					} catch ( FileNotFoundException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(rootPane, "Файл не найден!", "Ошибка!" ,JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(rootPane, "Ошибка при чтении файла!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}  catch (NullPointerException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(rootPane, "Неверный формат файла!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
					//^панель с визуальным представлением здания
					planPanel.removeAll();
					BuildingView buildingView = new BuildingView(building);
					planPanel.add(buildingView);
					planPanel.repaint();
					planPanel.revalidate();
					scrollPane.revalidate();
					//$панель с визуальным представлением здания
					buildingType.setText(building.getType());
					floorsCount.setText( String.valueOf(building.getCountFloor()) );
					totalAreaBuilding.setText( String.valueOf(building.getTotalSpace()) );
                }
			}
		});
		
		add(mainPanel);
	}

	/*class RadioButtonWithData extends JRadioButton
	{
		public RadioButtonWithData(LookAndFeel lAf)
		{
			
		}
	}*/
	
	class BuildingView extends Box
	{	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private IBuilding building;
		private JPanel panelFloor;
		
		public BuildingView(IBuilding building)
		{
			super(BoxLayout.Y_AXIS);
			this.building = building;
			panelFloor = null;
			for ( int f = building.getCountFloor()-1; f >= 0; f-- )
			{
				panelFloor = new FloorView(building.getFloor(f));
				panelFloor.setLayout(new FlowLayout());
				panelFloor.setBorder(BorderFactory.createTitledBorder("Floor "+(f+1)));
				add(panelFloor);
			}
		}
		
		
		private class FloorView extends JPanel
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public IFloor m_floor;
			
			public FloorView(IFloor floor)
			{
				this.m_floor = floor;
				this.setSize(150,40);
				
				for ( int p = 0; p <m_floor.getCountPlacement() ; p++ )
				{
					PlacementView pv = new PlacementView(m_floor.getPlacement(p), String.valueOf(building.getPlacementNumberBySpace(m_floor.getPlacement(p))+1)); 
					this.add(pv , "Placement "+(building.getPlacementNumberBySpace(m_floor.getPlacement(p))));
				}
				
				MouseAdapter ma = new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						floorNumber.setText( String.valueOf(building.getFloorNumberByFloor(m_floor)+1) );
						spacesCount.setText( String.valueOf(m_floor.getTotalRoom()) );
						totalAreaFloor.setText( String.valueOf(m_floor.getTotalSpace()) );
						
						spaceNumber.setText( "0" );
						roomsCount.setText( "0" );
						totalAreaSpace.setText( "0" );
					}
				};
				addMouseListener(ma);
			}
			
			private class PlacementView extends JButton
			{	
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				public ISpace m_placement;
				
				public PlacementView(ISpace placement)
				{
					m_placement = placement;
					//PlacementView thisPW = this;
					addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							/*	spaceNumber
							,	roomsCount
							,	totalAreaSpace;*/
							
							//thisPW.getParent().
							floorNumber.setText( String.valueOf(building.getFloorNumberByFloor(m_floor)+1) );
							spacesCount.setText( String.valueOf(m_floor.getTotalRoom()) );
							totalAreaFloor.setText( String.valueOf(m_floor.getTotalSpace()) );
							
							
							
							spaceNumber.setText( String.valueOf(building.getPlacementNumberBySpace(m_placement)+1) );
							roomsCount.setText( String.valueOf(m_placement.getCountRoom()) );
							totalAreaSpace.setText( String.valueOf(m_placement.getSpace()) );
						}
					});
				}
				
				public PlacementView(ISpace placement, String text)
				{
					this(placement);
					setText(text);
				}
			}
		}
		

	}
}

