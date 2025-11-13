package com.grupo2.cinemautn.persistence;

import com.grupo2.cinemautn.models.resena.Resena;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Genero;
import com.grupo2.cinemautn.models.contenido.Pelicula;
import com.grupo2.cinemautn.models.contenido.Serie;
import com.grupo2.cinemautn.models.contenido.TipoContenido;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;

public class GestoraContenidoJSON {
    private static final String DEFAULT_FILE = "data/contenido.json";

    //  SERIALIZAR
    public <C extends Collection<Contenido>> void listaToArchivo(C lista) {
        ArrayList<Contenido> arrayListContenido = new ArrayList<>(lista);
        OperacionesLectoEscritura.grabar(DEFAULT_FILE, serializarLista(arrayListContenido));
    }

    public JSONObject serializar(Contenido c) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            // campos comunes
            jsonObject.put("id", c.getId());
            jsonObject.put("titulo", c.getTitulo());
            jsonObject.put("genero", c.getGenero() != null ? c.getGenero().name() : JSONObject.NULL);
            jsonObject.put("anio", c.getAnio());
            jsonObject.put("director", c.getDirector());
            jsonObject.put("estado", c.isEstado());
            // nuevo campo imagenPortada
            jsonObject.put("imagenPortada", c.getImagenPortada() != null ? c.getImagenPortada() : JSONObject.NULL);

            // tipo y campos específicos
            if (c instanceof Pelicula) {
                jsonObject.put("tipo", TipoContenido.PELICULA.name());
                Pelicula p = (Pelicula) c;
                jsonObject.put("duracion", p.getDuracion());
            } else if (c instanceof Serie) {
                jsonObject.put("tipo", TipoContenido.SERIE.name());
                Serie s = (Serie) c;
                jsonObject.put("temporadas", s.getTemporadas());
                jsonObject.put("episodios", s.getEpisodios());
            } else {
                // Por defecto, no conocemos el tipo
                jsonObject.put("tipo", JSONObject.NULL);
            }

            // resenas (si existen)
            JSONArray arrRes = new JSONArray();
            if (c.getResenas() != null) {
                for (Resena res : c.getResenas()) {
                    JSONObject jres = new JSONObject();
                    jres.put("id", res.getId());
                    jres.put("idUsuario", res.getIdUsuario());
                    jres.put("estrellas", res.getEstrellas());
                    jres.put("estado", res.isEstado());
                    arrRes.put(jres);
                }
            }
            jsonObject.put("resenas", arrRes);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray serializarLista(ArrayList<Contenido> lista) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Contenido c: lista) {
                jsonArray.put(serializar(c)); // agrega el JSONObject al JSONArray
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // DESERIALIZAR

    public ArrayList<Contenido> archivoALista() {
        JSONTokener tokener = OperacionesLectoEscritura.leer(DEFAULT_FILE);
        ArrayList<Contenido> lista = new ArrayList<>();
        try {
            if (tokener != null) {
                lista = deserializarLista(new JSONArray(tokener));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Contenido deserializar (JSONObject jsonObject) {
        Contenido c = null;
        try {
            // leer tipo
            String tipoStr = jsonObject.optString("tipo", null);
            TipoContenido tipo = null;
            if (tipoStr != null && !"".equals(tipoStr) && !"null".equals(tipoStr)) {
                try {
                    tipo = TipoContenido.valueOf(tipoStr);
                } catch (IllegalArgumentException ex) {
                    tipo = null;
                }
            }

            // campos comunes
            String titulo = jsonObject.optString("titulo", null);
            String generoStr = jsonObject.optString("genero", null);
            Genero genero = null;
            if (generoStr != null && !"".equals(generoStr) && !"null".equals(generoStr)) {
                try {
                    genero = Genero.valueOf(generoStr);
                } catch (IllegalArgumentException ex) {
                    genero = null;
                }
            }
            int anio = jsonObject.optInt("anio", 0);
            String director = jsonObject.optString("director", null);
            String imagenPortada = jsonObject.optString("imagenPortada", null);

            // crear instancia segun tipo
            if (tipo == TipoContenido.PELICULA) {
                double duracion = jsonObject.optDouble("duracion", 0);
                Pelicula p = new Pelicula();
                p.setTitulo(titulo);
                p.setGenero(genero);
                p.setAnio(anio);
                p.setDirector(director);
                p.setEstado(jsonObject.optBoolean("estado", true));
                p.setId(jsonObject.optInt("id", 0));
                // duracion es privado sin setter; usar reflexion? Pelicula no tiene setter, pero tiene campos privados.
                // En lugar de reflection, creamos usando el constructor si tenemos todos los datos
                p.setId(jsonObject.optInt("id", 0));
                p.setImagenPortada(imagenPortada);
                p = new Pelicula(titulo, genero, anio, director, duracion, imagenPortada);
                c = p;
            } else if (tipo == TipoContenido.SERIE) {
                int temporadas = jsonObject.optInt("temporadas", 0);
                int episodios = jsonObject.optInt("episodios", 0);
                Serie s = new Serie();
                s.setTitulo(titulo);
                s.setGenero(genero);
                s.setAnio(anio);
                s.setDirector(director);
                s.setEstado(jsonObject.optBoolean("estado", true));
                s.setId(jsonObject.optInt("id", 0));
                s.setId(jsonObject.optInt("id", 0));
                s.setImagenPortada(imagenPortada);
                s = new Serie(titulo, genero, anio, director, temporadas, episodios, imagenPortada);
                c = s;
            }

            // resenas
            if (c != null) {
                c.setResenas(new ArrayList<>());
                JSONArray arrRes = jsonObject.optJSONArray("resenas");
                if (arrRes != null) {
                    for (int i = 0; i < arrRes.length(); i++) {
                        JSONObject jres = arrRes.optJSONObject(i);
                        if (jres != null) {
                            Resena res = new Resena();
                            res.setId(jres.optInt("id", 0));
                            res.setIdUsuario(jres.optInt("idUsuario", 0));
                            res.setEstrellas(jres.optInt("estrellas", 0));
                            res.setEstado(jres.optBoolean("estado", true));
                            c.agregarResena(res);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return c;
    }

    public ArrayList<Contenido> deserializarLista (JSONArray jsonArray) {
        ArrayList<Contenido> lista = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Contenido c = deserializar(jsonArray.getJSONObject(i));
                if (c != null) lista.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}