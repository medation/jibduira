import { Monument } from './../monument/monument';
import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams} from 'ionic-angular';

@Component({
  selector: 'page-monuments',
  templateUrl: 'monuments.html'
})
export class Monuments {

  user: string;
  message: string;
  monuments : any;
  item : any;
  spot: any;
  type : string;

  constructor(private authenticationService: AuthenticationService,
              private navParams: NavParams,
              private app: App,
              private utility: Utility,
              private httpClient: HttpClient,
              private monumentService : MonumentService) {
        
        this.item = {  title :"Search",  category:"null",  statue:"null", url: "null", resume:"null", chapitres: "null", nbrTome : 0 };

        this.user = navParams.data.user;
        this.spot = navParams.data.spot;
        this.type = navParams.data.type;
  }

  ionViewDidLoad() {   
        this.updateHangout();
  }

  updateHangout() {
        //Show loading
        var loading = this.utility.getLoader();
        loading.present();

        this.monumentService.getMonuments(this.type, this.spot.name).subscribe(data => {
        
            this.monuments = data;
             
           //Hide loading
            setTimeout(function(){
                loading.dismiss();
            },1000); 

        });        
  }

  getItems(ev: any) {
    // Reset items back to all of the items

    // set val to the value of the searchbar
    let val = ev.target.value;

        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            for(var i = 0;i<this.monuments.length;i++) { 
                if(this.monuments[i].name == val)
                {
                    this.item = this.monuments[i];
                }
            }
        }
        
    }

  goToMonument(monument){
    let nav = this.app.getRootNav();
    nav.push(Monument, {
            "monument" : monument,
            "user" : this.user
        });
  }

  back(){
      let nav = this.app.getRootNav();
        nav.push(Home);
  }

  logout() {
    this.authenticationService.logout();
  }

}
