package com.alfianpp.randomherogeneratormlbb;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.alfianpp.randomherogeneratormlbb.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AdView mAdView;

    private List<String> all_heroes = new ArrayList<>();
    private List<String> tank_heroes = new ArrayList<>();
    private List<String> fighter_heroes = new ArrayList<>();
    private List<String> assassin_heroes = new ArrayList<>();
    private List<String> mage_heroes = new ArrayList<>();
    private List<String> marksman_heroes = new ArrayList<>();
    private List<String> support_heroes = new ArrayList<>();
    private List<String> charge_heroes = new ArrayList<>();
    private List<String> burst_heroes = new ArrayList<>();
    private List<String> damage_heroes = new ArrayList<>();
    private List<String> push_heroes = new ArrayList<>();
    private List<String> reap_heroes = new ArrayList<>();
    private List<String> regen_heroes = new ArrayList<>();
    private List<String> poke_heroes = new ArrayList<>();
    private List<String> cc_heroes = new ArrayList<>();
    private List<String> initiator_heroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7582061309581400~2116909334");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        initializeAllHeroes();
        initializeHeroesByRole();
        initializeHeroesBySpeciality();
        doAllRandom();

        binding.btnGroupRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent grup = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(grup);
            }
        });

        binding.btnAllRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAllRandom();
            }
        });

        binding.btnRandomTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Tank");
            }
        });

        binding.btnRandomFighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Fighter");
            }
        });

        binding.btnRandomAssassin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Assassin");
            }
        });

        binding.btnRandomMage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Mage");
            }
        });

        binding.btnRandomMarksman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Marksman");
            }
        });

        binding.btnRandomSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomByRole("Support");
            }
        });

        binding.btnRandomCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Charge");
            }
        });

        binding.btnRandomBurst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Burst");
            }
        });

        binding.btnRandomDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Damage");
            }
        });

        binding.btnRandomPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Push");
            }
        });

        binding.btnRandomReap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Reap");
            }
        });

        binding.btnRandomRegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Regen");
            }
        });

        binding.btnRandomPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Poke");
            }
        });

        binding.btnRandomCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Crowd Control");
            }
        });

        binding.btnRandomInitiator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRandomBySpeciality("Initiator");
            }
        });
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

    private void initializeHeroesByRole(){
        List<String> hero_data;

        for(int i=0; i<all_heroes.size(); i++){
            hero_data = getSelectedHeroData(all_heroes.get(i));

            addHeroByRoleToList(all_heroes.get(i), hero_data.get(2));
            if(!TextUtils.isEmpty(hero_data.get(3))){
                addHeroByRoleToList(all_heroes.get(i), hero_data.get(3));
            }
        }
    }

    private void addHeroByRoleToList(String hero, String role){
        if(role.equals("Tank")){
            tank_heroes.add(hero);
        }else if(role.equals("Fighter")){
            fighter_heroes.add(hero);
        }else if(role.equals("Assassin")){
            assassin_heroes.add(hero);
        }else if(role.equals("Mage")){
            mage_heroes.add(hero);
        }else if(role.equals("Marksman")){
            marksman_heroes.add(hero);
        }else if(role.equals("Support")){
            support_heroes.add(hero);
        }
    }

    private void initializeHeroesBySpeciality(){
        List<String> hero_data;

        for(int i=0; i<all_heroes.size(); i++){
            hero_data = getSelectedHeroData(all_heroes.get(i));

            addHeroBySpecialityToList(all_heroes.get(i), hero_data.get(4));
            if(!TextUtils.isEmpty(hero_data.get(5))){
                addHeroBySpecialityToList(all_heroes.get(i), hero_data.get(5));
            }
        }
    }

    private void addHeroBySpecialityToList(String hero, String speciality){
        if(speciality.equals("Charge")){
            charge_heroes.add(hero);
        }else if(speciality.equals("Burst")){
            burst_heroes.add(hero);
        }else if(speciality.equals("Damage")){
            damage_heroes.add(hero);
        }else if(speciality.equals("Push")){
            push_heroes.add(hero);
        }else if(speciality.equals("Reap")){
            reap_heroes.add(hero);
        }else if(speciality.equals("Regen")){
            regen_heroes.add(hero);
        }else if(speciality.equals("Poke")){
            poke_heroes.add(hero);
        }else if(speciality.equals("Crowd Control")){
            cc_heroes.add(hero);
        }else if(speciality.equals("Initiator")){
            initiator_heroes.add(hero);
        }
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

    private void showSelectedHero(List<String> selected_hero){
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

    private void doAllRandom(){
        List<String> selected_hero = getSelectedHeroData(getRandomList(all_heroes));

        showSelectedHero(selected_hero);
    }

    private void doRandomByRole(String role){
        List<String> selected_hero = new ArrayList<>();

        if(role.equals("Tank")){
            selected_hero = getSelectedHeroData(getRandomList(tank_heroes));
        }else if(role.equals("Fighter")){
            selected_hero = getSelectedHeroData(getRandomList(fighter_heroes));
        }else if(role.equals("Assassin")){
            selected_hero = getSelectedHeroData(getRandomList(assassin_heroes));
        }else if(role.equals("Mage")){
            selected_hero = getSelectedHeroData(getRandomList(mage_heroes));
        }else if(role.equals("Marksman")){
            selected_hero = getSelectedHeroData(getRandomList(marksman_heroes));
        }else if(role.equals("Support")){
            selected_hero = getSelectedHeroData(getRandomList(support_heroes));
        }

        showSelectedHero(selected_hero);
    }

    private void doRandomBySpeciality(String speciality){
        List<String> selected_hero = new ArrayList<>();

        if(speciality.equals("Charge")){
            selected_hero = getSelectedHeroData(getRandomList(charge_heroes));
        }else if(speciality.equals("Burst")){
            selected_hero = getSelectedHeroData(getRandomList(burst_heroes));
        }else if(speciality.equals("Damage")){
            selected_hero = getSelectedHeroData(getRandomList(damage_heroes));
        }else if(speciality.equals("Push")){
            selected_hero = getSelectedHeroData(getRandomList(push_heroes));
        }else if(speciality.equals("Reap")){
            selected_hero = getSelectedHeroData(getRandomList(reap_heroes));
        }else if(speciality.equals("Regen")){
            selected_hero = getSelectedHeroData(getRandomList(regen_heroes));
        }else if(speciality.equals("Poke")){
            selected_hero = getSelectedHeroData(getRandomList(poke_heroes));
        }else if(speciality.equals("Crowd Control")){
            selected_hero = getSelectedHeroData(getRandomList(cc_heroes));
        }else if(speciality.equals("Initiator")){
            selected_hero = getSelectedHeroData(getRandomList(initiator_heroes));
        }

        showSelectedHero(selected_hero);
    }
}