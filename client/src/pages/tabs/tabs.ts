import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { Spot } from '../spot/spot';
import { City } from '../city/city';
import { Region } from '../region/region';
import { Home } from '../home/home';
import { Favorie } from '../favorie/favorie';
import { Circuit } from '../circuit/circuit';

@Component({
  templateUrl: 'tabs.html'
})
export class Tabs {
  // set the root pages for each tab
  tab1Root: any = Home;
  tab2Root: any = City;
  tab3Root: any = Region;
  tab4Root: any = Favorie;
  tab5Root: any = Circuit;
  mySelectedIndex: number;

  constructor(navParams: NavParams) {
    this.mySelectedIndex = navParams.data.tabIndex || 0;
  }

}