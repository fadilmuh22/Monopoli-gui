package com.pbo.monopoli.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;

public class Board extends JFrame {

	private JPanel contentPane;
	private JTextField txtNamaPetak;
	private JTextField txtHarga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Board() {
		setTitle("Monopoli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 485);
		
		SpringLayout sl_contentPane = new SpringLayout();
		
		final JPanel contentPane = new JPanel(sl_contentPane) {

		    @Override
		    public Dimension getPreferredSize() {
		         return new Dimension(1280, 720);
		     }
		};
		
		final JScrollPane scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(scrollPane);
		
		Papan papan = new Papan(sl_contentPane, contentPane);
		
		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, 60, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton);
		
		for (JPanel panel : papan.getListPanel()) {
			contentPane.add(panel);
		}
		
//		JPanel panel = new JPanel();
//		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 87, SpringLayout.NORTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 109, SpringLayout.WEST, contentPane);
//		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
//		contentPane.add(panel);
//		
//		txtNamaPetak = new JTextField();
//		sl_contentPane.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtNamaPetak, 0, SpringLayout.HORIZONTAL_CENTER, panel);
//		sl_contentPane.putConstraint(SpringLayout.VERTICAL_CENTER, txtNamaPetak, -txtNamaPetak.getPreferredSize().height, SpringLayout.VERTICAL_CENTER, panel);
//		txtNamaPetak.setFont(new Font("Arial", Font.PLAIN, 11));
//		txtNamaPetak.setHorizontalAlignment(SwingConstants.CENTER);
//		txtNamaPetak.setText("Nama Petak");
//		panel.add(txtNamaPetak);
//		txtNamaPetak.setColumns(10);
//		
//		CircleButton btnP = new CircleButton(" ");
//		btnP.setColor(Color.BLUE, Color.BLUE, Color.BLUE);
//		panel.add(btnP);
//		
//		txtHarga = new JTextField();
//		sl_contentPane.putConstraint(SpringLayout.NORTH, txtHarga, 3, SpringLayout.SOUTH, txtNamaPetak);
//
//		sl_contentPane.putConstraint(SpringLayout.WEST, txtHarga, 0, SpringLayout.WEST, txtNamaPetak);
//		
//		txtHarga.setText("Harga");
//		txtHarga.setHorizontalAlignment(SwingConstants.CENTER);
//		txtHarga.setFont(new Font("Arial", Font.PLAIN, 11));
//		txtHarga.setColumns(10);
//		panel.add(txtHarga);
		
	}
}
