package com.projeto.despesa.utilitarios;



import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.plaf.synth.SynthComboBoxUI;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author Romulo
 */


public class JCalendar extends JComboBox {

  private boolean showActualDate;
  private DatePopup datePopup;
  private JFormattedTextField formattedTextField;

  public JCalendar() {
    this(true);
  }
  
  public void setDate(Date data){
        try{
            formattedTextField.setText(new SimpleDateFormat("dd/MM/yyyy").format(data));
            setValue();
        }catch(Exception e){
            formattedTextField.setText("");
        }
    }
    
    public Date getDate(){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(formattedTextField.getText());
        } catch (ParseException ex) {
            return null;
        }
    }

  public JCalendar(boolean showActualDate) {
    /*
     * 'showActualDate': indica que, quando a caixa de texto for
     * (re)inicializada, o campo deve ou nao exibir a data atual.
     */
    super();
    this.showActualDate = showActualDate;
    MaskFormatter formatter = null;
    try {
      formatter = new MaskFormatter("##/##/####");
      formatter.setPlaceholderCharacter('_');
    } catch (ParseException e) {
    }

    formattedTextField = new JFormattedTextField(formatter);
    formattedTextField.setBorder(((JComponent) getEditor().getEditorComponent()).getBorder());
    if (showActualDate) {
      formattedTextField.setValue(getTime());
    } else {
      formattedTextField.setValue("");
    }
    formattedTextField.setFocusLostBehavior(JFormattedTextField.COMMIT);
    formattedTextField.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent evt) {
        setValue();
      }
    });

    setEditor(new BasicComboBoxEditor() {
      @Override
      public Component getEditorComponent() {
        return formattedTextField;
      }
    });
    super.setEditable(true);
  }

  private String getTime() {
    return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
  }

  private boolean isValidDate(String source) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);
      sdf.parse(source);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  private class MetalDateComboBoxUI extends MetalComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class WindowsDateComboBoxUI extends WindowsComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class MotifDateComboBoxUI extends MotifComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class SynthDateComboBoxUI extends SynthComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
      datePopup = new DatePopup();
      return datePopup;
    }
  }

  private class DatePopup extends BasicComboPopup {

    private DatePanel datePanel;

    public DatePopup() {
      super(JCalendar.this);
      removeAll();
    }

    public void constructDatePopup() {
      removeAll();
      setPreferredSize(new java.awt.Dimension(193, 142));
      setBorder(BorderFactory.createLineBorder(Color.BLACK));
      setLayout(new GridLayout(1, 1));
      datePanel = new DatePanel();
      if (hasValidDate()) {
        datePanel.prepareDate();
      } else {
        datePanel.prepare(false, false, false, false);
      }
      add(datePanel);
    }

    @Override
    public void show(Component invoker, int x, int y) {
      constructDatePopup();
      super.show(JCalendar.this, JCalendar.this.getWidth() - 194, JCalendar.this.getHeight());
    }

    @Override
    public void setVisible(boolean b) {
      if (b == false) {
        setValue();
        datePanel.stopTimer();
        datePanel = null;
      }

      super.setVisible(b);
    }
  }

  private class DatePanel extends javax.swing.JPanel {

    private GregorianCalendar gc;
    private Timer timer;
    private JLabel[] day;
    private String[] month;
    private JLabel selectedLabel;
    private int timerFlag = 0;
    private int dayOfMonth = 0;
    private int selectedDay = 0;
    private boolean pressed = false;
    private static final int TIME_FROZEN = 3;

    public DatePanel() {
      initComponents();
      gc = new GregorianCalendar();

      nextYear.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
          nextYear.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(true, false, true, false);
          }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
          nextYear.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(true, false, true, false);
            }
            timerFlag = 0;
          }
        }
      });

      nextMonth.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
          nextMonth.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(false, true, true, false);
          }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
          nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(false, true, true, false);
            }
            timerFlag = 0;
          }
        }
      });

      previousMonth.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
          previousMonth.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(false, true, false, false);
          }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
          previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(false, true, false, false);
            }
            timerFlag = 0;
          }
        }
      });

      previousYear.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
          previousYear.setBorder(BorderFactory.createLoweredBevelBorder());
          if (SwingUtilities.isLeftMouseButton(evt)) {
            initTimer(true, false, false, false);
          }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
          previousYear.setBorder(BorderFactory.createRaisedBevelBorder());

          if (SwingUtilities.isLeftMouseButton(evt)) {
            terminateTimer();
            if (timerFlag <= TIME_FROZEN) {
              prepare(true, false, false, false);
            }
            timerFlag = 0;
          }
        }
      });

      month = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
      day = new JLabel[42];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
      navegatePanel = new javax.swing.JPanel();
      previousYear = new javax.swing.JLabel();
      previousMonth = new javax.swing.JLabel();
      dateLabel = new javax.swing.JLabel();
      nextMonth = new javax.swing.JLabel();
      nextYear = new javax.swing.JLabel();
      weekAndDaysPanel = new javax.swing.JPanel();
      weekPanel = new javax.swing.JPanel();
      sundayLabel = new javax.swing.JLabel();
      mondayLabel = new javax.swing.JLabel();
      tuesdayLabel = new javax.swing.JLabel();
      wednesdayLabel = new javax.swing.JLabel();
      thursdayLabel = new javax.swing.JLabel();
      fridayLabel = new javax.swing.JLabel();
      saturdayLabel = new javax.swing.JLabel();
      daysPanel = new javax.swing.JPanel();

      setLayout(new java.awt.BorderLayout());

      navegatePanel.setLayout(null);

      navegatePanel.setPreferredSize(new java.awt.Dimension(20, 20));
      previousYear.setFont(new java.awt.Font("Arial", 0, 9));
      previousYear.setText("<<");
      previousYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      previousYear.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(previousYear);
      previousYear.setBounds(0, 0, 20, 20);

      previousMonth.setFont(new java.awt.Font("Arial", 0, 9));
      previousMonth.setText("<");
      previousMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      previousMonth.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(previousMonth);
      previousMonth.setBounds(20, 0, 20, 20);

      dateLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
      dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      dateLabel.setText("Abril, 2006");
      navegatePanel.add(dateLabel);
      dateLabel.setBounds(44, 3, 105, 14);

      nextMonth.setFont(new java.awt.Font("Arial", 0, 9));
      nextMonth.setText(">");
      nextMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      nextMonth.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(nextMonth);
      nextMonth.setBounds(151, 0, 20, 20);

      nextYear.setFont(new java.awt.Font("Arial", 0, 9));
      nextYear.setText(">>");
      nextYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      nextYear.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

      navegatePanel.add(nextYear);
      nextYear.setBounds(171, 0, 20, 20);

      add(navegatePanel, java.awt.BorderLayout.NORTH);

      weekAndDaysPanel.setLayout(new java.awt.BorderLayout());

      weekAndDaysPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      weekPanel.setLayout(new java.awt.GridLayout(1, 7));

      weekPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      weekPanel.setPreferredSize(new java.awt.Dimension(20, 20));
      sundayLabel.setForeground(new java.awt.Color(255, 0, 0));
      sundayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      sundayLabel.setText("D");
      weekPanel.add(sundayLabel);

      mondayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      mondayLabel.setText("S");
      weekPanel.add(mondayLabel);

      tuesdayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      tuesdayLabel.setText("T");
      weekPanel.add(tuesdayLabel);

      wednesdayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      wednesdayLabel.setText("Q");
      weekPanel.add(wednesdayLabel);

      thursdayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      thursdayLabel.setText("Q");
      weekPanel.add(thursdayLabel);

      fridayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      fridayLabel.setText("S");
      weekPanel.add(fridayLabel);

      saturdayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      saturdayLabel.setText("S");
      weekPanel.add(saturdayLabel);

      weekAndDaysPanel.add(weekPanel, java.awt.BorderLayout.NORTH);

      daysPanel.setLayout(new java.awt.GridLayout(6, 7));

      daysPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

      daysPanel.setPreferredSize(new java.awt.Dimension(95, 95));
      weekAndDaysPanel.add(daysPanel, java.awt.BorderLayout.CENTER);

      add(weekAndDaysPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>                        

    private void initTimer(final boolean b1, final boolean b2, final boolean b3, final boolean b4) {
      timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          if (timerFlag > TIME_FROZEN) {
            prepare(b1, b2, b3, b4);
          } else {
            timerFlag++;
          }
        }
      });

      timer.start();
    }

    private void terminateTimer() {
      timer.stop();
      timer = null;
    }

    private void initArrayOfDays() {
      for (int i = 0; i < 42; i++) {
        day[i] = new JLabel();
        day[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day[i].setOpaque(true);
        day[i].setBackground(Color.WHITE);
      }
    }

    private void fillDaysPanel(int firstDayOfMonth, int maximumDayOfMonth) {
      int index = 1;
      daysPanel.removeAll();
      initArrayOfDays();

      for (int i = 0; i < 42; i++) {
        if (i + 1 >= firstDayOfMonth && index <= maximumDayOfMonth) {
          day[i].setText(String.valueOf(index));
          day[i].setBorder(BorderFactory.createEmptyBorder());

          if (index == selectedDay || index == dayOfMonth) {
            day[i].setBorder(BorderFactory.createLoweredBevelBorder());

            if (index == selectedDay) {
              day[i].setBackground(Color.GRAY);
              day[i].setForeground(Color.WHITE);
              selectedLabel = day[i];
            } else {
              if (i % 7 != 0) {
                day[i].setForeground(UIManager.getDefaults().getColor("label.foreground"));
              } else {
                day[i].setForeground(Color.RED);
              }
            }
          } else {
            if (day[i].getText().equals(String.valueOf(selectedDay))) {
              day[i].setBackground(Color.GRAY);
              day[i].setForeground(Color.WHITE);
              selectedLabel = day[i];
            }

            if (day[i] != selectedLabel) {
              if (i % 7 != 0) {
                day[i].setForeground(UIManager.getDefaults().getColor("label.foreground"));
              } else {
                day[i].setForeground(Color.RED);
              }
            }
          }

          final int finalI = i;
          index++;

          day[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt)) {
                configureDayLabels(day[finalI]);
                pressed = true;
              }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt) && pressed) {
                configureDayLabels(day[finalI]);
              }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
              if (SwingUtilities.isLeftMouseButton(evt)) {
                if (getMousePosition() != null) {
                  datePopup.hide();
                  DecimalFormat df = new DecimalFormat("00");
                  String dayText = df.format(selectedDay);
                  String monthText = df.format(gc.get(Calendar.MONTH) + 1);
                  String yearText = String.valueOf(gc.get(Calendar.YEAR));
                  formattedTextField.setValue(dayText + "/" + monthText + "/" + yearText);
                }

                pressed = false;
              }
            }
          });
        }

        daysPanel.add(day[i]);
      }
    }

    private void setDaysAndFill() {
      gc.set(Calendar.DAY_OF_MONTH, 1);
      int firstDayOfMonth = gc.get(Calendar.DAY_OF_WEEK);
      int maximumDayOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
      gc.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      String monthText = month[gc.get(Calendar.MONTH)];
      dateLabel.setText(String.valueOf(monthText + ", " + gc.get(Calendar.YEAR)));
      fillDaysPanel(firstDayOfMonth, maximumDayOfMonth);
    }

    private void configureDayLabels(JLabel day) {
      if (!selectedLabel.getText().equals(String.valueOf(dayOfMonth))) {
        selectedLabel.setBorder(BorderFactory.createEmptyBorder());
      }

      selectedLabel.setBackground(Color.WHITE);

      gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(selectedLabel.getText()));
      if (gc.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        selectedLabel.setForeground(UIManager.getDefaults().getColor("label.foreground"));
      } else {
        selectedLabel.setForeground(Color.RED);
      }
      gc.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      day.setBorder(BorderFactory.createLoweredBevelBorder());
      day.setBackground(Color.GRAY);
      day.setForeground(Color.WHITE);
      selectedLabel = day;
      selectedDay = Integer.parseInt(selectedLabel.getText());
    }

    public void prepare(boolean rollYear, boolean rollMonth, boolean up, boolean instantiateGc) {
      if (instantiateGc) {
        gc = new GregorianCalendar();
      }
      dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
      if (selectedDay == 0 || instantiateGc) {
        selectedDay = dayOfMonth;
      }
      if (rollYear) {
        gc.roll(Calendar.YEAR, up);
      }

      if (rollMonth) {
        if ((up && gc.get(Calendar.MONTH) == Calendar.DECEMBER) || (!up && gc.get(Calendar.MONTH) == Calendar.JANUARY)) {
          gc.roll(Calendar.YEAR, up);
        }
        gc.roll(Calendar.MONTH, up);
      }

      setDaysAndFill();
    }

    public void prepareDate() {
      if (hasValidDate()) {
        int d = Integer.parseInt(formattedTextField.getValue().toString().substring(0, 2));
        int m = Integer.parseInt(formattedTextField.getValue().toString().substring(3, 5)) - 1;
        int y = Integer.parseInt(formattedTextField.getValue().toString().substring(6));
        dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        selectedDay = d;
        gc.set(Calendar.MONTH, m);
        gc.set(Calendar.YEAR, y);
        setDaysAndFill();
      } else {
        prepare(false, false, false, true);
      }
    }

    public void stopTimer() {
      if (timer != null) {
        timer.stop();
      }
    }
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel daysPanel;
    private javax.swing.JLabel fridayLabel;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JPanel navegatePanel;
    private javax.swing.JLabel nextMonth;
    private javax.swing.JLabel nextYear;
    private javax.swing.JLabel previousMonth;
    private javax.swing.JLabel previousYear;
    private javax.swing.JLabel saturdayLabel;
    private javax.swing.JLabel sundayLabel;
    private javax.swing.JLabel thursdayLabel;
    private javax.swing.JLabel tuesdayLabel;
    private javax.swing.JLabel wednesdayLabel;
    private javax.swing.JPanel weekAndDaysPanel;
    private javax.swing.JPanel weekPanel;
  }

  private void setValue() {
    if (isValidDate(formattedTextField.getText())) {
      formattedTextField.setValue(formattedTextField.getText());
    } else {
      formattedTextField.setText(null);
    }
  }

  @Override
  public void updateUI() {
    ComboBoxUI comboBoxUI = (ComboBoxUI) UIManager.getUI(JCalendar.this);
    if (comboBoxUI instanceof MetalComboBoxUI) {
      comboBoxUI = new MetalDateComboBoxUI();
    } else if (comboBoxUI instanceof MotifComboBoxUI) {
      comboBoxUI = new MotifDateComboBoxUI();
    } else if (comboBoxUI instanceof WindowsComboBoxUI) {
      comboBoxUI = new WindowsDateComboBoxUI();
    } else if (comboBoxUI instanceof SynthComboBoxUI) {
      comboBoxUI = new SynthDateComboBoxUI();
    }

    setUI(comboBoxUI);
  }

  @Override
  public boolean selectWithKeyChar(char keyChar) {
    return false;
  }

  @Override
  public void setEditable(boolean aFlag) {
    if (!aFlag) {
      throw new IllegalArgumentException("no sense in setting editable mode to false");
    }
  }

  @Override
  public Object getSelectedItem() {
    if (!formattedTextField.getValue().toString().contains("_")) {
      try {
        super.setSelectedItem(null);
        int d = Integer.parseInt(formattedTextField.getValue().toString().substring(0, 2));
        int m = Integer.parseInt(formattedTextField.getValue().toString().substring(3, 5)) - 1;
        int y = Integer.parseInt(formattedTextField.getValue().toString().substring(6));
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.DAY_OF_MONTH, d);
        gc.set(Calendar.MONTH, m);
        gc.set(Calendar.YEAR, y);
        return gc;
      } catch (StringIndexOutOfBoundsException ex) {
        return null;
      }
    } else {
      setSelectedItem(null);
      return null;
    }
  }

  @Override
  public void setSelectedItem(Object anObject) {
    if (anObject != null) {
      if (anObject instanceof GregorianCalendar) {
        String dayText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.DAY_OF_MONTH));
        String monthText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.MONTH));
        String yearText = String.valueOf(((GregorianCalendar) anObject).get(Calendar.YEAR));
        formattedTextField.setValue(dayText + "/" + monthText + "/" + yearText);
      } else {
        super.setSelectedItem(null);
        throw new IllegalArgumentException("invalid date");
      }
    } else {
        formattedTextField.setValue(null);
    }

    super.setSelectedItem(null);
  }

  public boolean hasValidDate() {
    return isValidDate(formattedTextField.getValue().toString());
  }

  public String getText() {
    return !formattedTextField.getText().equals("__/__/____") ? formattedTextField.getText() : "";
  }
}