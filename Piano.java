import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.midi.*; //creates piano sound

public class Piano implements ActionListener, KeyListener {
	//fields
	private JFrame pianoFrame;
	private GridBagLayout pianoLayout;
	private GridBagConstraints gbc;
	private JButton[] whiteKeys = new JButton[8];
	private JButton[] blackKeys = new JButton[5];
	
	public Piano() {
		//piano frame implementation
		pianoFrame = new JFrame();
		pianoFrame.setSize(1000, 500);
		pianoFrame.getContentPane().setBackground(Color.BLACK);
		pianoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pianoFrame.setTitle("Surya's Piano");
		pianoFrame.addKeyListener(this); //adds KeyListener to the entire frame
		
		//layout
		pianoLayout = new GridBagLayout();
		pianoFrame.setLayout(pianoLayout);
		
		//gbc for piano keys
		gbc = new GridBagConstraints();
		
		//piano keys
		gbc.gridx = 0;
		char key = 'C';
		for(int i = 0; i < 8; i++) {
			whiteKeys[i] = new JButton(String.valueOf(key));
			whiteKeys[i].addActionListener(this); //each button has an ActionListener
			whiteKeys[i].addKeyListener(this); //each button has a KeyListener	
			whiteKeys[i].setPreferredSize(new Dimension(50, 150)); //sets button size
			gbc.insets = new Insets(5, 5, 5, 5); //makes space between buttons bigger
			gbc.gridx += 2;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			pianoFrame.add(whiteKeys[i], gbc);
			key++;
			if(String.valueOf(key).equals("H")) {
				key = 'A';
			}	
		}
		gbc.gridx = 1;
		String[] blackKey = {"C#", "D#", "F#", "G#", "A#"};
		for(int i = 0; i < 5; i++) {
			blackKeys[i] = new JButton((blackKey[i]));
			blackKeys[i].addActionListener(this);
			blackKeys[i].addKeyListener(this);
			blackKeys[i].setBackground(Color.BLACK);
			blackKeys[i].setOpaque(true);
			blackKeys[i].setPreferredSize(new Dimension(40, 150));
			if(blackKey[i].equals("F#")) {
				gbc.gridx += 2;
			}
			gbc.gridx += 2;
			gbc.gridy = 0;	
			pianoFrame.add(blackKeys[i], gbc);
		}
		
		//methods necessary for KeyListeners to work, user can now press both the button or keyboard for the program to work
		pianoFrame.setFocusable(true);
		pianoFrame.requestFocus();
		
		//make everything visible
		pianoFrame.setVisible(true);
	}
	public static void main(String[] args) {
		//new piano object
		new Piano();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 3; i++) {
			if(e.getSource() == whiteKeys[i]) {
				try {
					//creating piano note for the first three keys C, D, E
					ShortMessage shortMessage = new ShortMessage();
					//key turns on, is taken from channel 4, 60th Midi note onwards, velocity 93
					shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 60 + 2 * i, 93); 
					//synthesizer is the instrument used
					Synthesizer synth = MidiSystem.getSynthesizer();
					//receiver generates the sound
					Receiver synthReceiver = synth.getReceiver();
					//opens synthesizer
					synth.open();
					//message sent to the synthesizer
					synthReceiver.send(shortMessage, -1);
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				} catch (InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
			}
		}
		for(int i = 3; i < 7; i++) {
			if(e.getSource() == whiteKeys[i]) {
				try {
					//creating piano note for the next 4 keys F, G, A, B 
					ShortMessage shortMessage = new ShortMessage();
					shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 59 + 2 * i, 70); 
					Synthesizer synth = MidiSystem.getSynthesizer();
					Receiver synthReceiver = synth.getReceiver();
					synth.open();
					synthReceiver.send(shortMessage, -1);
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				} catch (InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == whiteKeys[7]) {
			try {
				//creating piano note for the next C key
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 72, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		for(int i = 0; i < 2; i++) {
			if(e.getSource() == blackKeys[i]) {
				try {
					//creating piano note for the first 2 black keys C#, D#
					ShortMessage shortMessage = new ShortMessage();
					shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 61 + 2 * i, 93); 
					Synthesizer synth = MidiSystem.getSynthesizer();
					Receiver synthReceiver = synth.getReceiver();
					synth.open();
					synthReceiver.send(shortMessage, -1);
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				} catch (InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
			}
		}
		for(int i = 2; i < 5; i++) {
			if(e.getSource() == blackKeys[i]) {
				try {
					//creating piano note for the next 3 black keys F#, G#, A# 
					ShortMessage shortMessage = new ShortMessage();
					shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 62 + 2 * i, 93); 
					Synthesizer synth = MidiSystem.getSynthesizer();
					Receiver synthReceiver = synth.getReceiver();
					synth.open();
					synthReceiver.send(shortMessage, -1);
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				} catch (InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//each key on the keyboard pressed plays a particular note
		if(e.getKeyCode() == KeyEvent.VK_A) {
			try {
				//A corresponds with first C
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 60, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}	
		if(e.getKeyCode() == KeyEvent.VK_S) {
			try {
				//S corresponds with D
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 62, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			try {
				//D corresponds with first E
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 64, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
				
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			try {
				//F corresponds with F
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 65, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_G) {
			try {
				//G corresponds with G
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 67, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_H) {
			try {
				//H corresponds with A
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 69, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			try {
				//J corresponds with B
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 71, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
				
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			try {
				//K corresponds with second C
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 72, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			try {
				//W corresponds with C#
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 61, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_E) {
			try {
				//E corresponds with D#
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 63, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_T) {
			try {
				//T corresponds with F#
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 66, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Y) {
			try {
				//Y corresponds with G#
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 68, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_U) {
			try {
				//U corresponds with A#
				ShortMessage shortMessage = new ShortMessage();
				shortMessage.setMessage(ShortMessage.NOTE_ON, 4, 70, 93); 
				Synthesizer synth = MidiSystem.getSynthesizer();
				Receiver synthReceiver = synth.getReceiver();
				synth.open();
				synthReceiver.send(shortMessage, -1);
			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			} catch (InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
