package com.cmm.dev.calculator__;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Johann on 01.07.2015.
 */
public class SimpleMode {

    // Filtern und anschließendes Speichern von Symbolen in eigener Liste
    private static List<Character> getSym(String string)
    {
        List<Character> listOfSymbols=new LinkedList<Character>();
        for(int i=0;i<string.length();i++)
        {
            char symbol=string.charAt(i);

            if(symbol=='-' || symbol=='+' || symbol=='*' || symbol=='/')
            {
                listOfSymbols.add(symbol);
            }
        }
        return listOfSymbols;
    }

    // Zwischenliste Operanden
    private static List<String> getOp(String string)
    {
        String[] operandsArray=string.split("-|\\+|\\*|\\/");
        List<String> listOfOperands=new LinkedList<String>();

        for(int i=0;i<operandsArray.length;i++)
            listOfOperands.add(operandsArray[i]);

        return listOfOperands;
    }

    private static void listUpdater(List<Character> listOfSymbols,List<String> listOfOperands,int position,float result)
    {
        listOfSymbols.remove(position);
        listOfOperands.remove(position);
        listOfOperands.remove(position);
        listOfOperands.add(position,String.valueOf(result));
    }

    public static String solve(String eingabe)
    {
        List<Character> listSymbols=getSym(eingabe);
        List<String> listOperands=getOp(eingabe);
        int operationCount=listSymbols.size();
        float op1=0.0F;
        float op2=0.0F;
        float result=0.0F;

        while(operationCount>0)
        {
            if(listSymbols.contains('*') || listSymbols.contains('/'))
            {
                // Index des Symbols speichern
                int positionMultiplikation=listSymbols.indexOf('*');
                int positionDivision=listSymbols.indexOf('/');


                // fÃ¼ge Symbol in Symbolliste ein
                if((positionMultiplikation<positionDivision && positionMultiplikation!=-1) || positionDivision==-1)
                {
                    op1=Float.parseFloat(listOperands.get(positionMultiplikation));
                    op2=Float.parseFloat(listOperands.get(positionMultiplikation+1));
                    result=op1*op2;

                    listUpdater(listSymbols,listOperands,positionMultiplikation,result);
                }
                else if((positionMultiplikation>positionDivision && positionDivision!=-1) || positionMultiplikation==-1)
                {
                    op1=Float.parseFloat(listOperands.get(positionDivision));
                    op2=Float.parseFloat(listOperands.get(positionDivision+1));
                    result=op1/op2;

                    // neue EintrÃ¤ge speichern
                    listUpdater(listSymbols,listOperands,positionDivision,result);
                }

            }
            else if(listSymbols.contains('-') || listSymbols.contains('+'))
            {
                int positionSubtraktion=listSymbols.indexOf('-');
                int positionAddition=listSymbols.indexOf('+');

                if((positionSubtraktion<positionAddition && positionSubtraktion!=-1) || positionAddition==-1)
                {
                    op1=Float.parseFloat(listOperands.get(positionSubtraktion));
                    op2=Float.parseFloat(listOperands.get(positionSubtraktion+1));
                    result=op1-op2;

                    listUpdater(listSymbols,listOperands,positionSubtraktion,result);
                }
                else if((positionSubtraktion>positionAddition && positionAddition!=-1) || positionSubtraktion==-1)
                {

                    op1=Float.parseFloat(listOperands.get(positionAddition));
                    op2=Float.parseFloat(listOperands.get(positionAddition+1));
                    result=op1+op2;

                    listUpdater(listSymbols,listOperands,positionAddition,result);
                }

            }
            operationCount--;
        }

        Iterator<String> iterator=listOperands.iterator();

        String finalResult="";

        while(iterator.hasNext())
        {
            finalResult=iterator.next();
        }

        return finalResult;
    }
}
