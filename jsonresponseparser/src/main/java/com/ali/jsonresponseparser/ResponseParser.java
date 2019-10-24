package com.ali.jsonresponseparser;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseParser {

    private String data = "data";

    public <T> void addAndNotifyAdapter(@NonNull ArrayList<T> list, @NonNull JSONObject response, @NonNull RecyclerView.Adapter adapter, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        setNextPage(response, pageHandler);
        int oldSize = list.size();
        ArrayList<T> arrayList;
        JSONArray jsonArray = null;
        jsonArray = response.getJSONArray(data);
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
        int newSize = list.size();
        adapter.notifyItemRangeInserted(oldSize, newSize);
    }
    private <T> void addAndNotifyAdapter(@NonNull ArrayList<T> list, @NonNull JSONObject response, @NonNull ArrayAdapter adapter, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        setNextPage(response, pageHandler);
        ArrayList<T> arrayList;
        JSONArray jsonArray = null;
        jsonArray = response.getJSONArray(data);
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
        adapter.notifyDataSetChanged();
    }
    public <T> void add(@NonNull ArrayList<T> list, @NonNull JSONObject response, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        setNextPage(response, pageHandler);
        ArrayList<T> arrayList;
        JSONArray jsonArray = response.getJSONArray(data);
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
    }

    public <T> void addAndNotifyAdapter(@NonNull ArrayList<T> list, @NonNull JSONArray response, @NonNull RecyclerView.Adapter adapter, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        int oldSize = list.size();
        ArrayList<T> arrayList;
        Gson gson = new Gson();
        arrayList = gson.fromJson(response.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
        int newSize = list.size();
        adapter.notifyItemRangeInserted(oldSize, newSize);
    }
    private <T> void addAndNotifyAdapter(@NonNull ArrayList<T> list, @NonNull JSONArray response, @NonNull ArrayAdapter adapter, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        ArrayList<T> arrayList;
        Gson gson = new Gson();
        arrayList = gson.fromJson(response.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
        adapter.notifyDataSetChanged();
    }
    public <T> void add(@NonNull ArrayList<T> list, @NonNull JSONArray response, @Nullable PageHandler pageHandler, @NonNull Class<T> tClass) throws JSONException {
        ArrayList<T> arrayList;
        Gson gson = new Gson();
        arrayList = gson.fromJson(response.toString(), new ListOfGenerics<>(tClass));
        list.addAll(arrayList);
    }

    public String getResponseDataName() {
        return data;
    }

    public void setResponseDataName(String data) {
        this.data = data;
    }

    private void setNextPage(JSONObject response, PageHandler pageHandler) {
        try {
            if (pageHandler != null)
                pageHandler.setNextPage(response.getJSONObject("links").getString("next"));
        } catch (JSONException e) {
            e.printStackTrace();
            pageHandler.setNextPage("null");
        }
    }

    public interface PageHandler {
        void setNextPage(String nextPage);
    }
}
