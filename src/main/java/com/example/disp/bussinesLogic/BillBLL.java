package com.example.disp.bussinesLogic;

import com.example.disp.Dao.AbstractDAO;
import com.example.disp.model.BillRecord;
import java.beans.IntrospectionException;

public class BillBLL {
    private final AbstractDAO<BillRecord> billDAO;

    public BillBLL() {
        this.billDAO = new AbstractDAO<>();
    }

    public void insertBill(BillRecord billRecord) {
        try {
            billDAO.insert(billRecord);
            System.out.println("ok");
        } catch (IntrospectionException e) {
            System.err.println("bill no ok");
        }
    }
}
