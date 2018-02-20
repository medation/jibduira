import { Component , ViewChild ,ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Geolocation ,GeolocationOptions ,Geoposition ,PositionError } from '@ionic-native/geolocation'; 
import { Utility } from '../../providers/utility';
import { MonumentService } from '../../services/monument.service';
import 'rxjs/add/operator/map';

declare var google;

@Component({
    selector: 'page-map',
    templateUrl: 'map.html'
})
export class Map {

  @ViewChild('map') mapContainer: ElementRef;
  map: any;
  options : GeolocationOptions;
  currentPos : Geoposition;
  monuments : any;
  infoWindows : any;

  constructor(  public navCtrl: NavController, 
                public monumentService: MonumentService, 
                private geolocation : Geolocation,
                public utility : Utility) {
        this.infoWindows = [];
  }

  ionViewWillEnter() {
    this.getUserPosition();
    
  }


  getUserPosition(){
        this.options = {
            enableHighAccuracy : false
        };
        this.geolocation.getCurrentPosition(this.options).then((pos : Geoposition) => {

            this.currentPos = pos;     
            //this.addMap(pos.coords.latitude,pos.coords.longitude);
            this.displayGoogleMap();
            
        },(err : PositionError)=>{
            console.log("error : " + err.message);
        ;
        })
    }

  displayGoogleMap() {
    let latLng = new google.maps.LatLng(this.currentPos.coords.latitude,this.currentPos.coords.longitude);

    let mapOptions = {
      center: latLng,
      disableDefaultUI: true,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    

    this.map = new google.maps.Map(this.mapContainer.nativeElement, mapOptions);

    var userMarker = new google.maps.Marker({   position: latLng,
                                                title: "Position actuelle"});
    userMarker.setMap(this.map);
    this.addInfoWindowToMarker(userMarker);

    this.getMarkers();
  }

  getMarkers() {
    //Show loading
    var loading = this.utility.getLoader();
    loading.present();

    this.monumentService.getAllMonuments().subscribe(data => {
    
        this.monuments = data;
        this.addMarkersToMap(data);
       //Hide loading
        setTimeout(function(){
            loading.dismiss();
        },1000); 

    });   
  }

  addMarkersToMap(markers) {
    for(let marker of markers) {
      var position = new google.maps.LatLng(marker.latitude, marker.longitude);
      var monumentMarker = new google.maps.Marker({ position: position, 
                                                    animation: google.maps.Animation.DROP,
                                                    icon: 'http://maps.google.com/mapfiles/kml/pal2/icon2.png',
                                                    title: marker.name,
                                                    image : marker.urlToImage
                                                });
      monumentMarker.setMap(this.map);
      this.addInfoWindowToMarker(monumentMarker)
    }
  }

  addInfoWindowToMarker(marker) {
    var infoWindowContent = '<h6>' + marker.title + '</h6>';
    var infoWindow = new google.maps.InfoWindow({
      content: infoWindowContent
    });
    marker.addListener('click', () => {
      this.closeAllInfoWindows();
      infoWindow.open(this.map, marker);
    });
    this.infoWindows.push(infoWindow);
  }

  closeAllInfoWindows() {
    for(let window of this.infoWindows) {
      window.close();
    }
  }

}