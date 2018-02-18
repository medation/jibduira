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
        value: circuit,
        checked: false
      });
    }

    alert.addButton({
      text: 'Ok',
      handler: data => {
        var test : boolean = true;
        this.testSelectOpen = false;
        this.testCircuitResult = data;
        if(this.testCircuitResult != null){
          for(let monumentSearch of this.testCircuitResult.monuments){
            if(this.monument.id == monumentSearch.id){
                test = false;
                let toast = this.toastCtrl.create({
                  message: `Ce monument est déja présent dans votre circuit`,
                  duration: 2000,
                  position: "bottom"
                });
            
                toast.present(toast);
              }
          }
          if(test){
            this.circuitService.addMonumentToCircuit(data.id,this.monument).subscribe(data => {
              this.circuit = data; 
              this.goToCircuit(this.circuit)
            });
          }
        }else {
          let toast = this.toastCtrl.create({
            message: `Veuillez créer un circuit d'abords`,
            duration: 2000,
            position: "bottom"
          });
      
          toast.present(toast);
        }
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
