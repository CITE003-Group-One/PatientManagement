/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.model;

import java.util.ArrayList;
import java.util.List;
import org.one.patientmanagement.ui.core.dto.QueueData;


public class AppointmentListModel {
    private final List<QueueData> morningItems = new ArrayList<>();
    private final List<QueueData> afternoonItems = new ArrayList<>();
    private Runnable onChanged;

    public void setOnChanged(Runnable onChanged) {
        this.onChanged = onChanged;
    }

    public void loadFromList(List<QueueData> data) {
        morningItems.clear();
        afternoonItems.clear();
        for (QueueData d : data) {
            switch (d.block()) {
                case MORNING -> morningItems.add(d);
                case AFTERNOON -> afternoonItems.add(d);
                default -> throw new AssertionError(d.block().name());
            }
        }
        if (onChanged != null) onChanged.run();
    }

    public List<QueueData> getMorningItems() { return morningItems; }
    public List<QueueData> getAfternoonItems() { return afternoonItems; }

    public void add(QueueData data) {
        switch (data.block()) {
            case MORNING -> morningItems.add(data);
            case AFTERNOON -> afternoonItems.add(data);
            default -> throw new AssertionError(data.block().name());
        }
        if (onChanged != null) onChanged.run();
    }

    public void remove(QueueData data) {
        morningItems.remove(data);
        afternoonItems.remove(data);
        if (onChanged != null) onChanged.run();
    }

    public void clear() {
        morningItems.clear();
        afternoonItems.clear();
        if (onChanged != null) onChanged.run();
    }
}
