package br.com.socio.torcedor.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.socio.torcedor.model.Campanha;
/**
 * 
 * @author Luciano
 *
 */
public class CampanhaDTO {

	public static List<Campanha> transJsonClass(String dados) {
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(dados);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Campanha> lista = new ArrayList<>();
		Gson gson = new Gson();

		for (int i = 0; i < jsonArray.length(); i++) {
			Campanha obj = new Campanha();
			JSONObject jsonObject;
			try {
				jsonObject = jsonArray.getJSONObject(i);

				obj.setId(jsonObject.optInt("id"));
				obj.setNome(jsonObject.optString("nome"));
				obj.setDataIni(jsonObject.optString("dataIni"));
				obj.setDataFim(jsonObject.optString("dataFim"));

				lista.add(obj);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return lista;
	}

}
