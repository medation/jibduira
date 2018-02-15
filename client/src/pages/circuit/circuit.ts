import { Monument } from './../monument/monument';
import { Home } from './../home/home';
import { Utility } from './../../providers/utility';
import { MonumentService } from './../../services/monument.service';
import {Component} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../../services/authentication";
import {HttpClient} from "@angular/common/http";
import {App,NavParams, ModalController, ViewController} from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CircuitService } from '../../services/circuit.service';

@Component({
  selector: 'page-circuit',
  templateUrl: 'circuit.html'
})
export class Circuit {

  user: string;
  circuits : any;
  circuit : any;

  constructor(private authenticationService: AuthenticationService,
              private navParams: NavParams,
              public modalCtrl: ModalController,
              private app: App,
              private utility: Utility,
              private httpClient: HttpClient) {
    
                
  }

  ionViewDidLoad() {   
        this.updateHangout();
  }

  updateHangout() {
              
  }

  addCircuit() {
    let modal = this.modalCtrl.create(AddCircuitModal);
    modal.present();
  }

  doRefresh(refresher) {

        setTimeout(() => {
          refresher.complete();
        }, 500);

  }


  logout() {
    this.authenticationService.logout();
  }

}

@Component({
  selector: 'page-circuit',
  templateUrl: 'circuit-modal.html'
})
export class AddCircuitModal {

  form : FormGroup;
  hasError: boolean;
  errorMessage: string;
  circuits : any;

  constructor(private navParams: NavParams,
              public modalCtrl: ModalController,
              public viewCtrl : ViewController,
              private app: App,
              private circuitService : CircuitService,
              public formBuilder: FormBuilder,
              private httpClient: HttpClient) {
    
        this.form = this.formBuilder.group({
          name: ['', Validators.required]
        });       
  }

  onSubmit(): void {


    this.circuitService
        .addCircuit(this.form.value.name)
        .subscribe(data => {
            this.circuits = data;
        });
    }


  dismiss() {
    this.viewCtrl.dismiss();
  }
}

