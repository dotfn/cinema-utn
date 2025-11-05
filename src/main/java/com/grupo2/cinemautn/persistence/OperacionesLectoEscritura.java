package com.grupo2.cinemautn.persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.*;

public class OperacionesLectoEscritura {

    // SERIALIZAR

    public static void grabar(String nombreArchivo, JSONArray jsonArray) {
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            fileWriter.write(jsonArray.toString(4));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DESERIALIZAR

    public static JSONTokener leer(String nombreArchivo) {
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tokener;
    }
}