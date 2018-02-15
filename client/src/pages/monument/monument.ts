import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams} from 'ionic-angular';

@Component({
  selector: 'page-monument',
  templateUrl: 'monument.html'
})

export class Monument {

  user: string;
  message: string;
  monument : any;
  item : any;

  constructor(private authenticationService: AuthenticationService,
              private navParams: NavParams,
              private app: App,
              private utility: Utility,
              private httpClient: HttpClient,
              private monumentService : MonumentService) {
        
        this.user = navParams.data.user;
        this.monument = navParams.data.monument;
  }

  addMonumentFavorie(){
    var loading = this.utility.getLoader();
        loading.present();

        this.monumentService.addMonumentFavorie(this.monument.id).subscribe(data => {
        
            this.monument = data;
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
