import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams, ToastController, AlertController} from 'ionic-angular';
import { CircuitService } from '../../services/circuit.service';
import { CircuitModal } from '../circuit/circuit';

@Component({
  selector: 'page-monument',
  templateUrl: 'monument.html'
})

export class Monument {

  user: string;
  message: string;
  monument : any;
  item : any;
  testSelectOpen: boolean;
  testCircuitResult;
  circuits : any;
  circuit : any;

  constructor(private authenticationService: AuthenticationService,
              private navParams: NavParams,
              private app: App,
              public alerCtrl: AlertController,
              public toastCtrl: ToastController,
              private utility: Utility,              
              private circuitService : CircuitService,
              private httpClient: HttpClient,
              private monumentService : MonumentService) {
        
        this.user = navParams.data.user;
        this.monument = navParams.data.monument;
        this.updateHangout();

  }

  updateHangout() {
    //Show loading
    var loading = this.utility.getLoader();
    loading.present();

    this.circuitService.getCircuits().subscribe(data => {
    
        this.circuits = data;
         
       //Hide loading
        setTimeout(function(){
            loading.dismiss();
        },1000); 

    });
}

  addMonumentFavorie(){

        this.monumentService.addMonumentFavorie(this.monument.id).subscribe(data => {
        
            this.monument = data;

        });

        let toast = this.toastCtrl.create({
          message: `Ce monument est maintenant dans vos favories`,
          duration: 2000,
          position: "bottom"
        });
    
        toast.present(toast);
  }

  doSelectCircuit(){

    

    let alert = this.alerCtrl.create();
    alert.setTitle('Choisissez un circuit');

    for(let circuit of this.circuits){
      alert.addInput({
        type: 'radio',
        label: circuit.name,
        value: circuit.id,
        checked: false
      });
    }

    alert.addButton({
      text: 'Ok',
      handler: data => {
        this.testSelectOpen = false;
        this.testCircuitResult = data;
        this.circuitService.addMonumentToCircuit(data,this.monument).subscribe(data => {
          this.circuit = data; 
          this.goToCircuit(this.circuit)
        });
      }
    });

    alert.present().then(() => {
      this.testSelectOpen = true;
    });

  }

  goToCircuit(circuit){
    let nav = this.app.getRootNav();
    nav.push(CircuitModal, {
      "circuit" : circuit
    });
  }

  logout() {
    this.authenticationService.logout();
  }

}
