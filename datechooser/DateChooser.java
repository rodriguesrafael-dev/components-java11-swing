package br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DateChooser extends javax.swing.JPanel {

    public JTextField getTextRefernce() {
        return textReference;
    }

    public void addEventDateChooser(EventDateChooser event) {
        events.add(event);
    }

    private JTextField textReference;

    private final String MONTH_BRAZILIAN_PORTUGUESE[] = {
        "Janeiro",
        "Fevereiro",
        "Marco",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    };
    
    private String dateFormat = "dd/MM/yyyy";
    private int MONTH = 1;
    private int YEAR = 2021;
    private int DAY = 1;
    private int STATUS = 1;   //  1 is day    2 is month  3 is year
    private int startYear;
    private SelectedDate selectedDate = new SelectedDate();
    private List<EventDateChooser> events;

    public DateChooser() {
        initComponents();
        execute();
    }

    private void execute() {
        setForeground(new Color(204, 93, 93));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    public void setTextRefernce(JTextField txt) {
        this.textReference = txt;
        this.textReference.setEditable(false);
        this.textReference.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (textReference.isEnabled()) {
                    showPopup();
                }
            }
        });
        setText(false, 0);
    }

    private void setText(boolean runEvent, int act) {
        if (textReference != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date date = df.parse(DAY + "/" + MONTH + "/" + YEAR);
                textReference.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
        if (runEvent) {
            runEvent(act);
        }
    }

    private void runEvent(int act) {
        SelectedAction action = () -> act;
        events.forEach(event -> {
            event.dateSelected(action, selectedDate);
        });
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

    public void toDay() {
        toDay(true);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToLeft(dates)) {
            cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToRight(dates)) {
            cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
        slide.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
            slide.slideToLeft(years);
        }
    }

    public void showPopup(Component com, int x, int y) {
        popup.show(com, x, y);
    }

    public void showPopup() {
        popup.show(textReference, 0, textReference.getHeight());
    }

    public void hidePopup() {
        popup.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        popup = new javax.swing.JPopupMenu() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(114, 113, 113));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(Color.WHITE);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        header = new javax.swing.JPanel();
        cmdForward = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        MY = new javax.swing.JLayeredPane();
        cmdMonth = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        lb = new javax.swing.JLabel();
        cmdYear = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        cmdPrevious = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button();
        slide = new br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Slider();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(204, 93, 93));
        header.setMaximumSize(new java.awt.Dimension(262, 40));

        cmdForward.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/grupolider/app_impressao_etiqueta_pallet/shared/components/datechooser/forward.png"))); 
        cmdForward.setFocusable(true);
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener((java.awt.event.ActionEvent evt) -> {
            cmdForwardActionPerformed(evt);
        });

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0);
        flowLayout1.setAlignOnBaseline(true);
        MY.setLayout(flowLayout1);

        cmdMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdMonth.setForeground(new java.awt.Color(255, 255, 255));
        cmdMonth.setText("January");
        cmdMonth.setFocusPainted(false);
        cmdMonth.setFont(new java.awt.Font("Kh Content", 0, 14)); 
        cmdMonth.setPaintBackground(false);
        cmdMonth.addActionListener((java.awt.event.ActionEvent evt) -> {
            cmdMonthActionPerformed(evt);
        });
        MY.add(cmdMonth);

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("-");
        MY.add(lb);

        cmdYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdYear.setForeground(new java.awt.Color(255, 255, 255));
        cmdYear.setText("2018");
        cmdYear.setFocusPainted(false);
        cmdYear.setFont(new java.awt.Font("Kh Content", 0, 14)); 
        cmdYear.setPaintBackground(false);
        cmdYear.addActionListener((java.awt.event.ActionEvent evt) -> {
            cmdYearActionPerformed(evt);
        });
        MY.add(cmdYear);

        cmdPrevious.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/grupolider/app_impressao_etiqueta_pallet/shared/components/datechooser/previous.png"))); 
        cmdPrevious.setFocusable(true);
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener((java.awt.event.ActionEvent evt) -> {
            cmdPreviousActionPerformed(evt);
        });
        cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdPreviousKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MY, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmdPrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmdForward, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        slide.setLayout(new javax.swing.BoxLayout(slide, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {
        switch (STATUS) {
            case 1:
                if (MONTH == 1) {
                    MONTH = 12;
                    YEAR--;
                } else {
                    MONTH--;
                }
                setDateBack();
                break;
                
            case 3:
                setYearBack();
                break;
                
            default:
                if (YEAR >= 1000) {
                    YEAR--;
                    Months months = new Months();
                    months.setEvent(getEventMonth());
                    slide.slideToLeft(months);
                    cmdYear.setText(YEAR + "");
                }
                break;
        }
    }

    private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {
        switch (STATUS) {
            case 1:
                if (MONTH == 12) {
                    MONTH = 1;
                    YEAR++;
                } else {
                    MONTH++;
                }
                setDateNext();
                break;
                
            case 3:
                setYearNext();
                break;
                
            default:
                YEAR++;
                Months months = new Months();
                months.setEvent(getEventMonth());
                slide.slideToLeft(months);
                cmdYear.setText(YEAR + "");
                break;
        }
    }

    private void cmdMonthActionPerformed(java.awt.event.ActionEvent evt) {
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdYearActionPerformed(java.awt.event.ActionEvent evt) {
        if (STATUS != 3) {
            STATUS = 3;
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.showYear(YEAR);
            slide.slideToDown(years);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdPreviousKeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP: {
                Component com = slide.getComponent(0);
                if (com instanceof Dates) {
                    Dates d = (Dates) com;
                    d.up();
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                Component com = slide.getComponent(0);
                if (com instanceof Dates) {
                    Dates d = (Dates) com;
                    d.down();
                }
                break;
            }
            case KeyEvent.VK_LEFT: {
                Component com = slide.getComponent(0);
                if (com instanceof Dates) {
                    Dates d = (Dates) com;
                    d.back();
                }
                break;
            }
            case KeyEvent.VK_RIGHT: {
                Component com = slide.getComponent(0);
                if (com instanceof Dates) {
                    Dates d = (Dates) com;
                    d.next();
                }
                break;
            }
            default:
                break;
        }
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setSelectedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String d = df.format(date);
        DAY = Integer.valueOf(d.split("/")[0]);
        MONTH = Integer.valueOf(d.split("/")[1]);
        YEAR = Integer.valueOf(d.split("/")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MY;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmdForward;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmdMonth;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmdPrevious;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Button cmdYear;
    private javax.swing.JPanel header;
    private javax.swing.JLabel lb;
    private javax.swing.JPopupMenu popup;
    private br.com.grupolider.app_impressao_etiqueta_pallet.shared.components.datechooser.Slider slide;
    // End of variables declaration//GEN-END:variables

    public SelectedDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(SelectedDate selectedDate) {
        this.selectedDate = selectedDate;
        DAY = selectedDate.getDay();
        MONTH = selectedDate.getMonth();
        YEAR = selectedDate.getYear();
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_BRAZILIAN_PORTUGUESE[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            toDay(false);
        }
    }

}
