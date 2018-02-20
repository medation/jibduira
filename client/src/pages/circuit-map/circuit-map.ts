import { Component , ViewChild ,ElementRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Geolocation ,GeolocationOptions ,Geoposition ,PositionError } from '@ionic-native/geolocation'; 
import { Utility } from '../../providers/utility';
import { MonumentService } from '../../services/monument.service';
import 'rxjs/add/operator/map';

declare var google;

@Component({
    selector: 'page-circuit-map',
    templateUrl: 'circuit-map.html'
})
export class CircuitMap {

    @ViewChild('map') mapElement: ElementRef;
    @ViewChild('directionsPanel') directionsPanel: ElementRef;
    map: any;

    options : GeolocationOptions;
    currentPos : Geoposition;
    monuments : any;
    infoWindows : any;
    waypts : any = [];
    constructor(public navCtrl: NavController,
                private navParams: NavParams,
                public monumentService: MonumentService, 
                private geolocation : Geolocation,
                public utility : Utility) {
 
          this.monuments = navParams.data.circuit.monuments;
          for(let monument of this.monuments){
            this.waypts.push({
              location: {lat: monument.latitude, lng: monument.longitude},
              stopover: true
            });
          }
          
    }
 
    ionViewDidLoad(){
 
        this.getUserPosition()
        
 
    }

    getUserPosition(){
        this.options = {
            enableHighAccuracy : false
        };
        this.geolocation.getCurrentPosition(this.options).then((pos : Geoposition) => {

            this.currentPos = pos;     
            this.loadMap();
            this.startNavigating();
          
        },(err : PositionError)=>{
            console.log("error : " + err.message);
        })
    }
 
    loadMap(){
 
        let latLng = new google.maps.LatLng(this.currentPos.coords.latitude,this.currentPos.coords.longitude);
 
        let mapOptions = {
          center: latLng,
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
 
        this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
       
    }
 
    startNavigating(){
 
        let directionsService = new google.maps.DirectionsService;
        let directionsDisplay = new google.maps.DirectionsRenderer;
 
        directionsDisplay.setMap(this.map);
        directionsDisplay.setPanel(this.directionsPanel.nativeElement);
 
        directionsService.route({
            origin: new google.maps.LatLng(this.currentPos.coords.latitude,this.currentPos.coords.longitude),
            destination: new google.maps.LatLng(this.currentPos.coords.latitude,this.currentPos.coords.longitude),
            travelMode: google.maps.TravelMode['DRIVING'],
            waypoints: this.waypts,
        }, (res, status) => {
 
            if(status == google.maps.DirectionsStatus.OK){
                directionsDisplay.setDirections(res);
            } else {
                console.warn(status);
            }
 
        });
 
    }

}