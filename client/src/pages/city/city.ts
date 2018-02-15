import { Monuments } from './../monuments/monuments';
import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams} from 'ionic-angular';

@Component({
  selector: 'page-city',
  templateUrl: 'city.html'
})
export class City {

  user: string;
  message: string;
  cities = [];
  item : any;

  constructor(  private authenticationService: AuthenticationService,
                private navParams: NavParams,
                private app: App,
                private utility: Utility,
                private httpClient: HttpClient,
                private monumentService : MonumentService) {
                    
        this.item = {  title :"Search",  category:"null",  statue:"null", url: "null", resume:"null", chapitres: "null", nbrTome : 0 };

        this.user = navParams.data.user;
        this.searchCity();
        
  }

  getItems(ev: any) {
    // Reset items back to all of the items

    // set val to the value of the searchbar
    let val = ev.target.value;

        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            for(var i = 0;i<this.cities.length;i++) { 
                if(this.cities[i].name == val)
                {
                    this.item = this.cities[i];
                }
            }
        }
        
    }

  goToMonuments(spot){
    let nav = this.app.getRootNav();
    nav.push(Monuments, {
            "spot" : spot,
            "type" : "City",
            "user" : this.user
        });
  }


  searchCity() {
    //Show loading
        var loading = this.utility.getLoader();
        loading.present();

        this.monumentService.getCities().subscribe(data => {
        
            this.cities = data;
            
           //Hide loading
            setTimeout(function(){
                loading.dismiss();
            },1000); 

        });
  }


  logout() {
    this.authenticationService.logout();
  }

}
