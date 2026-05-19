import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Corretto l'URL in /api/users per combaciare perfettamente con Spring Boot
  private apiUrl = 'http://localhost:8080/api/users'; 

  constructor(private http: HttpClient) {}

  // Legge tutti gli utenti
  getUsers(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  // Crea un nuovo utente
  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  // Elimina un utente tramite ID
  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}