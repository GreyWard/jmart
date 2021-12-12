package com.MichaelHardityaJmartFA;

import java.util.Date;

import com.MichaelHardityaJmartFA.dbjson.Serializable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**Class to keep Complaints data for further research
 *@author Michael Harditya*/
public class Complaint extends Serializable
{
    public Date date;
    public String desc;
    /**Keep Complaints data for further research and action
     * @param date record of the first recorded Complaint {@link Date}
     * @param desc description of the Complaint
     */
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }
    /**@deprecated read the Complaint data, since it is public, it can directly readed
     */
    public boolean read(String content){
        return false;
    }
    /**convert the Complaint information to String data
     * @returns String of Complaint information in "Complaint{date=formatted,desc=description" format
     */
    public String toString(){
        Calendar date = Calendar.getInstance();
        date.setTime(this.date);
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        return ("Complaint{date="+form.format(date.getTime())+",desc='"+desc+"'}");
    }
}
