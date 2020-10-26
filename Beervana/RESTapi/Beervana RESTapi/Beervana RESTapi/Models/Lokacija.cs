using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Lokacija
    {
        public Lokacija()
        {
            DegustacijskiMeni = new HashSet<DegustacijskiMeni>();
            DogadajNaLokaciji = new HashSet<DogadajNaLokaciji>();
            Obilazak = new HashSet<Obilazak>();
            OmiljenaLokacija = new HashSet<OmiljenaLokacija>();
            PromocijaNaLokaciji = new HashSet<PromocijaNaLokaciji>();
            Recenzija = new HashSet<Recenzija>();
        }

        public int IdLokacija { get; set; }
        public string NazivLokacije { get; set; }
        public string OibLokacije { get; set; }
        public string AdresaLokacije { get; set; }
        public string BrojTelefona { get; set; }
        public string Email { get; set; }
        public string CjenovniRazred { get; set; }
        public byte? PristupacnostOsobeSInvaliditetom { get; set; }
        public string RadnoVrijeme { get; set; }
        public string RadnoVrijemeVikend { get; set; }
        public byte? Pusenje { get; set; }
        public string DatumKreiranja { get; set; }
        public int IdKorisnik { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual ICollection<DegustacijskiMeni> DegustacijskiMeni { get; set; }
        public virtual ICollection<DogadajNaLokaciji> DogadajNaLokaciji { get; set; }
        public virtual ICollection<Obilazak> Obilazak { get; set; }
        public virtual ICollection<OmiljenaLokacija> OmiljenaLokacija { get; set; }
        public virtual ICollection<PromocijaNaLokaciji> PromocijaNaLokaciji { get; set; }
        public virtual ICollection<Recenzija> Recenzija { get; set; }
    }
}
