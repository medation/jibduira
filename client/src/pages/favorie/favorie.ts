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
  selector: 'page-favorie',
  templateUrl: 'favorie.html'
})
export class Favorie {

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
    

  }

  ionViewDidLoad() {   
        this.updateHangout();
  }

  updateHangout() {
        //Show loading
        var loading = this.utility.getLoader();
        loading.present();

        this.monumentService.getFavories().subscribe(data => {
        
            this.monuments = data;
             
           //Hide loading
            setTimeout(function(){
                loading.dismiss();
            },1000); 

        });        
  }


  goToMonument(monument){
    let nav = this.app.getRootNav();
    nav.push(Monument, {
            "monument" : monument,
            "user" : this.user
        });
  }

  logout() {
    this.authenticationService.logout();
  }

}
