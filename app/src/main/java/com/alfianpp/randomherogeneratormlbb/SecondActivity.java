package com.alfianpp.randomherogeneratormlbb;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.alfianpp.randomherogeneratormlbb.databinding.ActivitySecondBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList; 
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    private List<String> all_heroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        initializeAllHeroes();
        doAllRandom();
    }

    private void initializeAllHeroes() {
        String line;

        InputStream is = getResources().openRawResource(R.raw.heroes_mlbb);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        try {
            while((line = reader.readLine()) != null){
                all_heroes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        all_heroes.remove(0);
    }

    private void doAllRandom(){
        List<List<String>> selectedHeros = new ArrayList<>();
        for(int i=0; i<2; i++) {
            List<String> selected_hero = getSelectedHeroData(getRandomList(all_heroes));
            selectedHeros.add(selected_hero);
        }

        showSelectedHero(selectedHeros);
    }

    private List<String> getSelectedHeroData(String selected_hero){
        List<String> list = new ArrayList<>();

        String[] hero_data = selected_hero.split(",");
        for(int i=0; i<hero_data.length; i++){
            list.add(hero_data[i]);
        }

        return list;
    }

    private String getRandomList(List<String> list){
        Random random = new Random();

        return list.get(random.nextInt(list.size()));
    }

    private void showSelectedHero(List<List<String>> selectedHeros){
        int count = 0;
        for (List<String> selected_hero : selectedHeros){
            count++;
            if(count==1)
                showNumberOne(selected_hero);
            else if(count==2)
                showNumberTwo(selected_hero);
        }
    }

    private void showNumberOne(List<String> selected_hero){
        binding.imgHeroHead.setImageResource(binding.imgHeroHead.getContext().getResources().getIdentifier("img_hero_head_id"+selected_hero.get(0), "drawable", binding.imgHeroHead.getContext().getPackageName()));
        binding.txtHeroName.setText(selected_hero.get(1));
        if(TextUtils.isEmpty(selected_hero.get(3))){
            binding.txtHeroRole.setText(selected_hero.get(2));
        }else{
            binding.txtHeroRole.setText(selected_hero.get(2) + "/" + selected_hero.get(3));
        }
        if(TextUtils.isEmpty(selected_hero.get(5))){
            binding.txtHeroSpeciality.setText(selected_hero.get(4));
        }else{
            binding.txtHeroSpeciality.setText(selected_hero.get(4) + "/" + selected_hero.get(5));
        }
    }

    private void showNumberTwo(List<String> selected_hero){
        binding.imgHeroHead1.setImageResource(binding.imgHeroHead1.getContext().getResources().getIdentifier("img_hero_head_id"+selected_hero.get(0), "drawable", binding.imgHeroHead.getContext().getPackageName()));
        binding.txtHeroName1.setText(selected_hero.get(1));
        if(TextUtils.isEmpty(selected_hero.get(3))){
            binding.txtHeroRole1.setText(selected_hero.get(2));
        }else{
            binding.txtHeroRole1.setText(selected_hero.get(2) + "/" + selected_hero.get(3));
        }
        if(TextUtils.isEmpty(selected_hero.get(5))){
            binding.txtHeroSpeciality1.setText(selected_hero.get(4));
        }else{
            binding.txtHeroSpeciality1.setText(selected_hero.get(4) + "/" + selected_hero.get(5));
        }
    }
}
