package com.CodeExamples.OpenMRS;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ManageAppointments extends BaseTest{

    String URL ="http://demo.openmrs.org/openmrs/appointmentschedulingui/manageAppointmentTypes.page";


    @Test

    public void AddReg() {
        LoginAdminPharmacy();
        webDriver.get("http://demo.openmrs.org/openmrs/appointmentschedulingui/manageAppointmentTypes.page");

        WebElement numentries = webDriver.findElement(By.id("appointmentTypesTable_info"));

        String[] numsplit = numentries.getText().split(" ");
        String indexed = numsplit[5];

        webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/button")).click();





    }
}


