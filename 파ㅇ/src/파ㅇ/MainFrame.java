package 파ㅇ;

import java.awt.*;

import javax.swing.*;
/*
 * Author: 이범희
 * Created: 2008년 1월 26일 토요일 오전 1:09:47
 * Modified: 2008년 1월 26일 토요일 오전 1:09:47
 * E-Mail : vamalboro@nate.com
 * URL    : http://abum.tistory.com/
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//크게 프레임을 제작한 것입니다.
public class MainFrame extends JFrame {

	private MineGUI			mineGUI			= null;	
	/* menu */
	private JMenuBar		menuBar			= null;
	private JMenu			fileMenu		= null;
	private JMenuItem		newItem			= null;

	private JMenuItem		exitItem		= null;
	
	private JMenu			helpMenu		= null;
	private JMenuItem		helpItem		= null;
	private JMenuItem		inforItem		= null;
	private JMenuItem		storyItem		= null;

	private String			helpString		= "양쪽에 배열 박스가 있습니다.\n왼쪽은 자신의 위치와 목표인 푸른깃발이\n오른쪽은 그 점수가 나옵니다."
			+ "\n룰은 간단합니다.\n지뢰를 밝지 않고 목표에 가면 됩니다.\n지뢰에 가까울수록 점수가 높으니 이점 유의하세요.\n \n 참고로 지뢰수와 map의 크기 조절은 Protocol.java에서 가능합니다. ";
	private String			inforString		= "제작자 : PARK HYUN\n제작날짜 : 2017. 05~ 06\n\nGL LOVER     in IASA";
	private String			endString		= "프로그램을 종료 하시겠습니까?";
	private String			storyString		= "당신은 현재 세계 제 3차대전에 참전을 하였습니다.\n한국군이 전쟁에서 맹활약하였지만 전세가 역전되어 작전상 후퇴가 결정되었습니다."
			+ "\n예기치못하게 적군도 한 지역에서 후퇴하는 것으로 감지되어 다시 전진 명령이 내려졌습니다.\n현재 map상에는 적군은 없지만 적군이 설치한 지뢰가 있는 것으로 예측이 됩니다."
			+ "\n당신에게 주어진 도구는 금속탐지기로 지뢰가 가까이 있으면 수치가 높아집니다.\n상부에서는 지뢰제거반이 곧 투입될 것으로 미리 지뢰의 위치를 파악하라는 영령이 내려왔습니다."
			+ "\n금속 탐지기의 수치를 올리면서도 목표지점까지 안전하게 도착하시기 바랍니다.\n ";
	
	public MainFrame( String title ) {
		super( title );
		initMenu();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.mineGUI = new MineGUI();
		this.setContentPane(this.mineGUI);
		this.pack();
		this.setCenterPosition();
		// frame icon 
		MediaTracker tracker = new MediaTracker( this );
		Image img = Toolkit.getDefaultToolkit().getImage( "IMG/reset.jpg" );
		tracker.addImage( img, 0 );
		setIconImage( img );
		this.setResizable(false);
		this.setVisible(true);
	}//실행
	
	/* Inner class [ActionListener]*/
	class newActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeGame();
		}
	}
	class exitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result;
			result = JOptionPane.showConfirmDialog( MainFrame.this, endString, "Game End", 
														JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			if ( result == JOptionPane.YES_OPTION ) System.exit(0);
		}
	}
	class dialogActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == helpItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, helpString, "Help", JOptionPane.INFORMATION_MESSAGE );
			} else if ( e.getSource() == inforItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, inforString, "Information", JOptionPane.INFORMATION_MESSAGE );
			}else if ( e.getSource() == storyItem ) {
				JOptionPane.showMessageDialog( MainFrame.this, storyString, "Story~!", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	} // Inner class end


	/* Member Method */
	public void initMenu() {
		menuBar			= new JMenuBar();
		fileMenu		= new JMenu( "게임" );
		newItem			= new JMenuItem( "새 게임" );
		exitItem		= new JMenuItem( "끝내기" );
		
		newItem.addActionListener( new newActionListener() );
		exitItem.addActionListener( new exitActionListener() );
		
		fileMenu.add( newItem );
		fileMenu.addSeparator();
		fileMenu.add( exitItem );
		
		menuBar.add( fileMenu );
		
		helpMenu		= new JMenu( "도움말" );
		helpItem		= new JMenuItem( "HELP" );
		storyItem		= new JMenuItem( "STory" );
		inforItem		= new JMenuItem( "제작 정보" );
		
		helpItem.addActionListener( new dialogActionListener() );
		inforItem.addActionListener( new dialogActionListener() );
		storyItem.addActionListener( new dialogActionListener() );
		
		helpMenu.add( helpItem );
		helpMenu.add( storyItem );
		helpMenu.addSeparator();
		helpMenu.add( inforItem );
		
		menuBar.add( helpMenu );
		
		this.setJMenuBar( menuBar );
	}//큰 구성
	public void setCenterPosition() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int)( screen.getWidth() / 2 - frm.getWidth() / 2 );
		int ypos = (int)( screen.getHeight() / 2 - frm.getHeight() / 2 );
		this.setLocation( xpos, ypos );
	}//창의 위치

	public void changeGame() {
		this.remove(this.mineGUI);
		this.mineGUI = new MineGUI();
		this.setContentPane(this.mineGUI);
		this.pack();
		this.setCenterPosition();
		this.setResizable(false);
		this.setVisible(true);
	}//새게임 클릭시 실행
	public static void main ( String args[] )
	{
		new MainFrame( "파ㅇ" );//창의 이름.
	}
}

