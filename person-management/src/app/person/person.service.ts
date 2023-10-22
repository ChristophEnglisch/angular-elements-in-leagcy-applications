import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { PersonDto, NewPerson } from '../shared/PersonDto';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private baseUrl = `${environment.apiUrl}person`;
  constructor(private http: HttpClient) { }

  getPersons(): Observable<PersonDto[]> {
    return this.http.get<PersonDto[]>(this.baseUrl);
  }

  addPerson(newPerson: NewPerson): Observable<any> {
    return this.http.post(this.baseUrl, newPerson);
  }

  deletePerson(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  updatePerson(id: number, updatedPerson: NewPerson): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, updatedPerson);
  }
}
