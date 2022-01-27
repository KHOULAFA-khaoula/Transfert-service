package com.ensa.transfertservice.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class TransfertGenerator extends SequenceStyleGenerator {
    private static final String prefix = "EDB837";
   // @Override
   /* public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String suffix = "";

       /* try {
            Connection con = session.connection();
            Statement stat = con.createStatement();
            String sql = "select id.nextval from dual";
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                int seqval = rs.getInt(1);
                suffix = String.valueOf(seqval);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prefix + suffix;*/
       /* for(int i=0;i<7;i++){
            Random random = new Random();
            suffix =  prefix+random.nextInt(97);
        }*/
        @Override
        public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
            return prefix + String.valueOf(generateID());
        }

        public static char[] generateID() {

            //Creating object of Random class
            Random obj = new Random();

            char[] otp = new char[5];

            for (int i = 0; i < 7; i++) {
                otp[i] = (char) (obj.nextInt(10) + 48);
            }
            System.out.print("Your OTP is : " + otp.toString());
            return otp;
        }
    }