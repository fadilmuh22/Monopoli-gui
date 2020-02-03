package com.pbo.monopoli.views;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.pbo.monopoli.models.Monopoli;
import com.pbo.monopoli.utils.ActionsHelper;

public class Papan {
	private SpringLayout sl_contentPane;
	private JPanel contentPane;
	private ArrayList<JPanel> listPanel = new ArrayList<JPanel>();
	public int margin = 3, width = 80, height = 60;
	
	Monopoli monopoli = new Monopoli();
    
	
	public ArrayList<JPanel> getListPanel() {
		return listPanel;
	}
	
	public Papan(SpringLayout sl_contentPane, JPanel contentPane) {
		monopoli.setup();
		this.sl_contentPane = sl_contentPane;
		this.contentPane = contentPane;
		for (int i = 0; i < 40; i++) {
			listPanel.add(new JPanel());
		}
		createTopPanel();
		createLeftPanel();
		createBottomPanel();
		createRightPanel();
	}
	
	public void createTopPanel() {
		for (int i = 20; i <= 30; i++) {
			JPanel tempPanel = listPanel.get(i);
			
			if (i == 20) {
				sl_contentPane.putConstraint(SpringLayout.NORTH, tempPanel, 10, SpringLayout.NORTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.WEST,tempPanel, 10, SpringLayout.WEST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.SOUTH,tempPanel, height, SpringLayout.NORTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, tempPanel, width, SpringLayout.WEST, contentPane);
			} else {
				sl_contentPane.putConstraint(SpringLayout.NORTH, tempPanel, 10, SpringLayout.NORTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.WEST, tempPanel, margin, SpringLayout.EAST, listPanel.get(i-1));
				sl_contentPane.putConstraint(SpringLayout.SOUTH, tempPanel, 0, SpringLayout.SOUTH, listPanel.get(i-1));
				sl_contentPane.putConstraint(SpringLayout.EAST, tempPanel, width, SpringLayout.EAST, listPanel.get(i-1));
			}
			
			if (monopoli.getPetak(i).getKomplek().equals("E")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 0, 10, 0, Color.RED)
				);
				
			} else if (monopoli.getPetak(i).getKomplek().equals("F")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 0, 10, 0, Color.YELLOW)
				);
			}
			
			JLabel[] panelText =  setPanelText(
				tempPanel,
				monopoli.getPetak(i).getNamaPetak(),
				monopoli.getPetak(i).getHarga()
			);
			tempPanel.add(panelText[0], panelText[0]);
			
			listPanel.set(i, tempPanel);
		}
	}
	
	public void createLeftPanel() {
		for (int i = 19; i >= 10; i--) {
			JPanel tempPanel = listPanel.get(i);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, tempPanel, margin, SpringLayout.SOUTH, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.WEST, tempPanel, 0, SpringLayout.WEST, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.SOUTH, tempPanel, height, SpringLayout.SOUTH, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.EAST, tempPanel, 0, SpringLayout.EAST, listPanel.get(i+1));
			
			if (monopoli.getPetak(i).getKomplek().equals("C")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 0, 0, 10, Color.MAGENTA)
				);
				
			} else if (monopoli.getPetak(i).getKomplek().equals("D")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 0, 0, 10, Color.ORANGE)
				);
			}
			
			JLabel[] panelText =  setPanelText(
				tempPanel,
				monopoli.getPetak(i).getNamaPetak(),
				monopoli.getPetak(i).getHarga()
			);
			tempPanel.add(panelText[0], panelText[0]);
			
			listPanel.set(i, tempPanel);
		}
	}	
	
	public void createBottomPanel() {
		for (int i = 9; i >= 0; i--) {
			JPanel tempPanel = listPanel.get(i);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, tempPanel, 0, SpringLayout.NORTH, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.WEST, tempPanel, margin, SpringLayout.EAST, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.SOUTH, tempPanel, 0, SpringLayout.SOUTH, listPanel.get(i+1));
			sl_contentPane.putConstraint(SpringLayout.EAST, tempPanel, width, SpringLayout.EAST, listPanel.get(i+1));
			
			if (monopoli.getPetak(i).getKomplek().equals("A")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(10, 0, 0, 0, Color.GRAY)
				);
				
			} else if (monopoli.getPetak(i).getKomplek().equals("B")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(10, 0, 0, 0, Color.CYAN)
				);
			}
			
			JLabel[] panelText =  setPanelText(
				tempPanel,
				monopoli.getPetak(i).getNamaPetak(),
				monopoli.getPetak(i).getHarga()
			);
			tempPanel.add(panelText[0], panelText[0]);
			
			listPanel.set(i, tempPanel);
		}
	}
	
	public void createRightPanel() {
		for (int i = 31; i <= 39; i++) {
			JPanel tempPanel = listPanel.get(i);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, tempPanel, 3, SpringLayout.SOUTH, listPanel.get(i-1));
			sl_contentPane.putConstraint(SpringLayout.WEST, tempPanel, 0, SpringLayout.WEST, listPanel.get(i-1));
			sl_contentPane.putConstraint(SpringLayout.SOUTH, tempPanel, height, SpringLayout.SOUTH, listPanel.get(i-1));
			sl_contentPane.putConstraint(SpringLayout.EAST, tempPanel, 0, SpringLayout.EAST, listPanel.get(i-1));
			
			if (monopoli.getPetak(i).getKomplek().equals("G")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 10, 0, 0, Color.GREEN)
				);
				
			} else if (monopoli.getPetak(i).getKomplek().equals("H")) {
				tempPanel.setBorder(
						BorderFactory.createMatteBorder(0, 10, 0, 0, Color.BLUE)
				);
			}
			
			JLabel[] panelText =  setPanelText(
				tempPanel,
				monopoli.getPetak(i).getNamaPetak(),
				monopoli.getPetak(i).getHarga()
			);
			tempPanel.add(panelText[0], panelText[0]);
			
			listPanel.set(i, tempPanel);
		}
		
	}
	
	public JLabel[] setPanelText(JPanel panel, String nama, double harga) {
		JLabel txtNamaPetak;
		JLabel txtHarga;
		
		txtNamaPetak = new JLabel();
		
		if (nama.split(" ").length > 1) {
			txtNamaPetak.setText(
					"<html>" +
					nama.split(" ")[0] +
					"<br>" +
					nama.split(" ")[1] +
					"</html>"
			);
		} else if (nama.length() <= 5) {
			txtNamaPetak.setText(
					"<html>" +
					"<br>" +
					nama +
					"</html>"
			);
		} else {
			txtNamaPetak.setText(nama);			
		}
		
		txtNamaPetak.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNamaPetak.setHorizontalAlignment(SwingConstants.CENTER);
		
		sl_contentPane.putConstraint(
			SpringLayout.VERTICAL_CENTER, 
			txtNamaPetak, 
			-txtNamaPetak.getPreferredSize().height, 
			SpringLayout.VERTICAL_CENTER, 
			panel
		);
		
		panel.add(txtNamaPetak);
		
		txtHarga = new JLabel();
		if (harga != 0) {
			txtHarga.setText( "" + harga );
			txtHarga.setHorizontalAlignment(SwingConstants.CENTER);
			txtHarga.setFont(new Font("Arial", Font.PLAIN, 11));
			panel.add(txtHarga);
		}
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtHarga, 20, SpringLayout.SOUTH, txtNamaPetak);
		
		
		JLabel[] texts = new JLabel[] { txtNamaPetak, txtHarga }; 
		
		return texts;
	}
	
}
