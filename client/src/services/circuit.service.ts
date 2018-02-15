import { Observable } from 'rxjs/Observable';
import { Injectable }  from '@angular/core';
import { Http, Response } from '@angular/http';
import {HttpClient} from "@angular/common/http";
import {ReplaySubject} from "rxjs";
import {Storage} from "@ionic/storage";
import {JwtHelperService} from "@auth0/angular-jwt";
import 'rxjs/add/operator/map';

const SERVER_URL = "http://localhost:5000/api";

@Injectable()
export class CircuitService {
  
    private jwtTokenName = 'jwt_token';

    authUser = new ReplaySubject<any>(1);

    constructor(private httpClient: HttpClient,
                private http: Http,
                private storage: Storage,
                private jwtHelper: JwtHelperService) {
	}

    public addCircuit(name : string): Observable<any> {
        const url = `${SERVER_URL}/addCircuit`;
        return this.httpClient.post(url, name);    
    }

}