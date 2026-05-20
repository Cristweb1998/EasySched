import { Component, OnInit, signal } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { UserService } from './services/user'; 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  template: `
    <!-- Barra di Navigazione Superiore -->
    <nav class="navbar navbar-dark bg-dark shadow-sm mb-4">
      <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">
          <i class="bi bi-calendar-check me-2"></i>EasySched Admin
        </span>
        <span class="badge bg-success">Backend Connesso</span>
      </div>
    </nav>

    <div class="container">
      <!-- Intestazione della Dashboard -->
      <div class="row mb-4 align-items-center">
        <div class="col-md-6">
          <h2 class="text-secondary fw-bold m-0">Dashboard Utenti</h2>
          <p class="text-muted text-sm mb-0">Gestione e monitoraggio degli account registrati</p>
        </div>
        <div class="col-md-6 text-md-end mt-3 mt-md-0">
          <button class="btn btn-primary shadow-sm" (click)="toggleForm()">
            <i class="bi bi-person-plus-fill me-2"></i>{{ mostraForm ? 'Chiudi Modulo' : 'Nuovo Utente' }}
          </button>
        </div>
      </div>

      <!-- Modulo a comparsa per inserire un Nuovo Utente -->
      <div *ngIf="mostraForm" class="card shadow-sm border-0 mb-4 bg-light">
        <div class="card-body">
          <h5 class="card-title fw-bold text-secondary mb-3">Inserisci Nuovo Utente</h5>
          <div class="row g-3">
            <div class="col-md-5">
              <input type="text" class="form-control" placeholder="Nome Completo" [(ngModel)]="nuovoUtente.nome">
            </div>
            <div class="col-md-5">
              <input type="email" class="form-control" placeholder="Indirizzo Email" [(ngModel)]="nuovoUtente.email">
            </div>
            <div class="col-md-2 d-grid">
              <button class="btn btn-success" (click)="aggiungiUtente()">Salva</button>
            </div>
          </div>
        </div>
      </div>

      <!-- Card Principale con la Tabella -->
      <div class="card shadow-sm border-0 mb-4">
        <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
          <h5 class="m-0 text-dark fw-semibold">Lista Utenti</h5>
          <span class="badge bg-primary rounded-pill">Totale: {{ utenti().length }}</span>
        </div>
        
        <div class="card-body p-0">
          <!-- Messaggio se non ci sono utenti -->
          <div *ngIf="utentsEmpty()" class="p-5 text-center text-muted">
            <i class="bi bi-people text-secondary display-4 d-block mb-3"></i>
            <p class="lead m-0">Nessun utente trovato o in attesa del backend...</p>
          </div>

          <!-- Tabella Bootstrap Reattiva -->
          <div *ngIf="!utentsEmpty()" class="table-responsive">
            <table class="table table-hover align-middle mb-0">
              <thead class="table-light text-uppercase fs-7 text-secondary">
                <tr>
                  <th class="ps-4" style="width: 10%">ID</th>
                  <th style="width: 45%">Nome</th>
                  <th style="width: 30%">Email</th>
                  <th class="text-end pe-4" style="width: 15%">Azioni</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let u of utenti()">
                  <td class="ps-4 text-muted fw-bold">#{{ u.id }}</td>
                  <td>
                    <div class="d-flex align-items-center">
                      <div class="avatar-placeholder me-3">
                        {{ u.nome ? u.nome.charAt(0).toUpperCase() : '?' }}
                      </div>
                      <span class="fw-semibold text-dark">{{ u.nome }}</span>
                    </div>
                  </td>
                  <td class="text-muted">{{ u.email }}</td>
                  <td class="text-end pe-4">
                    <button class="btn btn-sm btn-outline-danger border-0" title="Elimina" (click)="eliminaUtente(u.id)">
                      <i class="bi bi-trash3-fill"></i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- Debug Reattivo -->
      <div class="card border-0 shadow-sm mb-5">
        <div class="card-header bg-light py-2" (click)="toggleDebug()" style="cursor: pointer; user-select: none;">
          <div class="d-flex justify-content-between align-items-center text-secondary">
            <span class="fw-medium"><i class="bi bi-bug me-2"></i> Strumenti di Debug Sviluppatore</span>
            <i class="bi" [ngClass]="mostraDebug ? 'bi-chevron-up' : 'bi-chevron-down'"></i>
          </div>
        </div>
        
        <div *ngIf="mostraDebug" class="card-body bg-dark text-light p-3 rounded-bottom">
          <pre class="m-0 text-success" style="font-size: 0.85rem; max-height: 250px; overflow-y: auto;">{{ utenti() | json }}</pre>
        </div>
      </div>
    </div>
  `
})
export class App implements OnInit {
  utenti = signal<any[]>([]);
  mostraForm = false; 
  mostraDebug = false; 
  nuovoUtente = { nome: '', email: '' };

  constructor(private userService: UserService) {}

  utentsEmpty(): boolean {
    return this.utenti().length === 0;
  }

  ngOnInit() {
    this.caricaUtenti();
  }

  caricaUtenti() {
    this.userService.getUsers().subscribe({
      next: (data: any) => {
        let datiEstratti: any[] = [];
        if (Array.isArray(data)) {
          datiEstratti = data;
        } else if (data && data.content) {
          datiEstratti = data.content;
        } else {
          datiEstratti = [data]; 
        }
        this.utenti.set(datiEstratti);
      },
      error: (err: any) => {
        console.error("ERRORE DURANTE LA SCARICAMENTO DATI (GET):", err);
      }
    });
  }

  toggleForm() {
    this.mostraForm = !this.mostraForm;
  }

  toggleDebug() {
    this.mostraDebug = !this.mostraDebug;
  }

  aggiungiUtente() {
    if (!this.nuovoUtente.nome || !this.nuovoUtente.email) {
      alert("Per favore, compila sia il Nome che l'Email!");
      return;
    }

    const utenteDaInviare = {
      nome: this.nuovoUtente.nome,
      email: this.nuovoUtente.email
    };

    this.userService.createUser(utenteDaInviare).subscribe({
      next: (utenteSalvato: any) => {
        this.caricaUtenti(); 
        this.nuovoUtente = { nome: '', email: '' }; 
        this.mostraForm = false; 
      },
      error: (err: any) => console.error("ERRORE DURANTE IL SALVATAGGIO:", err)
    });
  }

  eliminaUtente(id: number) {
    if (confirm("Sei sicuro di voler eliminare definitivamente questo utente?")) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.caricaUtenti(); 
        },
        error: (err: any) => console.error("ERRORE DURANTE L'ELIMINAZIONE:", err)
      });
    }
  }
}