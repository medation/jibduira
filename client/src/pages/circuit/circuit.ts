import { Monument } from './../monument/monument';
import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams, ModalController, ViewController, ToastController} from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CircuitService } from '../../services/circuit.service';
import { CircuitMap } from '../circuit-map/circuit-map';

@Component({
  selector: 'page-circuit',
  templateUrl: 'circuit.html'
})
export class Circuit {

  user: string;
  circuits : any;
  circuit : any;
  errorMessage: string;
  form : FormGroup;

  constructor(private authenticationService: AuthenticationService,
              private navParams: NavParams,
              public modalCtrl: ModalController,
              private app: App,
              public toastCtrl: ToastController,
              public formBuilder : FormBuilder,
              private circuitService : CircuitService,
              private utility: Utility,
              private httpClient: HttpClient) {
    
          this.form = this.formBuilder.group({
            name: ['', Validators.required]
          });
  }

  ionViewDidLoad() {   
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

  onSubmit(): void {
    this.circuitService
        .addCircuit(this.form.value.name)
        .subscribe(data => {
            this.updateHangout();
        });

        let toast = this.toastCtrl.create({
          message: `Le nouveau circuit a été ajouté avec succée`,
          duration: 2000,
          position: "bottom"
        });
    
        toast.present(toast);
  }


  doRefresh(refresher) {

        setTimeout(() => {
          this.updateHangout();
          refresher.complete();
        }, 500);

  }

  goToCircuit(circuit){
    let nav = this.app.getRootNav();
    nav.push(CircuitModal, {
      "circuit" : circuit
    });
  }

  deleteCircuit(circuit){
    //Show loading
    var loading = this.utility.getLoader();
    loading.present();

    this.circuitService.deleteCircuit(circuit.id).subscribe(data => {
    
        this.circuits = data;
         
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




@Component({
  selector: 'page-circuit',
  templateUrl: 'circuit-modal.html'
})
export class CircuitModal {

  circuit : any;
  mode : any;

  constructor(private navParams: NavParams,
              public modalCtrl: ModalController,
              public viewCtrl : ViewController,
              private app: App,
              private utility: Utility, 
              private circuitService : CircuitService,
              public formBuilder: FormBuilder,
              private httpClient: HttpClient) {
    
      this.circuit = navParams.data.circuit;
      this.mode = "WALKING";
  }

  ionViewDidLoad() {
    this.updateHangout();
  }

  updateHangout() {
      //Show loading
      var loading = this.utility.getLoader();
      loading.present();

      this.circuitService.getCircuit(this.circuit.id).subscribe(data => {
      
          this.circuit = data;
          
        //Hide loading
          setTimeout(function(){
              loading.dismiss();
          },1000); 

      });
  }

  doRefresh(refresher) {

      setTimeout(() => {
        this.updateHangout();
        refresher.complete();
      }, 500);

  }

  goToMonument(monument){
    let nav = this.app.getRootNav();
    nav.push(Monument, {
            "monument" : monument
        });
  }

  dismiss() {
    this.viewCtrl.dismiss();
  }

  tracerCircuit(){
    let nav = this.app.getRootNav();
    nav.push(CircuitMap, {
            "circuit" : this.circuit,
            "mode" : this.mode
        });
  }
}


