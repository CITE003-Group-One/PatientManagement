/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller;

public abstract class AbstractController<
        V extends ControllerBound<C>,
        C extends AbstractController<V, C>
> {

    protected final V view;

    protected AbstractController(V view) {
        this.view = view;
        view.setController((C) this);
    }

    public V getView() {
        return view;
    }
}