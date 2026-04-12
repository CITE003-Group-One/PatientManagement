/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller;

public abstract class AbstractComponentController<
        V extends ControllerBound<C>,
        C extends AbstractComponentController<V, C>
> {

    protected V view;

    public void attachTo(V view) {
        this.view = view;
        view.setController((C) this); // safe cast
        onAttached();
    }

    public V getView() {
        return view;
    }
    
    public abstract void onAttached();
}