using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Korisnik
    {
        public Korisnik()
        {
            DegustacijskiMeni = new HashSet<DegustacijskiMeni>();
            Dogadaj = new HashSet<Dogadaj>();
            Lokacija = new HashSet<Lokacija>();
            Obilazak = new HashSet<Obilazak>();
            OmiljenaLokacija = new HashSet<OmiljenaLokacija>();
            OmiljenoPivo = new HashSet<OmiljenoPivo>();
            Promocija = new HashSet<Promocija>();
            Recenzija = new HashSet<Recenzija>();
        }
        internal AppDb Db { get; set; }
        internal Korisnik(AppDb db)
        {
            Db = Db;
        }
        public int IdKorisnik { get; set; }
        public int IdUloga { get; set; }
        public int IdClanstvo { get; set; }
        public string ImeKorisnika { get; set; }
        public string PrezimeKorisnika { get; set; }
        public string AdresaKorisnika { get; set; }
        public string EmailKorisnika { get; set; }
        public string TelefonKorisnika { get; set; }
        public string KorsnickoIme { get; set; }
        public string Lozinka { get; set; }
        public string SlikaKorisnika { get; set; }
        public string DatumRođenja { get; set; }
        public DateTime DatumRegistracije { get; set; }
        public string Status { get; set; }

        public virtual Clanstvo IdClanstvoNavigation { get; set; }
        public virtual Uloga IdUlogaNavigation { get; set; }
        public virtual ICollection<DegustacijskiMeni> DegustacijskiMeni { get; set; }
        public virtual ICollection<Dogadaj> Dogadaj { get; set; }
        public virtual ICollection<Lokacija> Lokacija { get; set; }
        public virtual ICollection<Obilazak> Obilazak { get; set; }
        public virtual ICollection<OmiljenaLokacija> OmiljenaLokacija { get; set; }
        public virtual ICollection<OmiljenoPivo> OmiljenoPivo { get; set; }
        public virtual ICollection<Promocija> Promocija { get; set; }
        public virtual ICollection<Recenzija> Recenzija { get; set; }
    }
}
