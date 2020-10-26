using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Recenzija
    {
        public int IdRecenzija { get; set; }
        public int? IdProizvod { get; set; }
        public int IdKorisnik { get; set; }
        public int? IdLokacija { get; set; }
        public int Ocjena { get; set; }
        public string Komentar { get; set; }
        public DateTime DatumIVrijemeRecenzije { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual Lokacija IdLokacijaNavigation { get; set; }
        public virtual Proizvod IdProizvodNavigation { get; set; }
    }
}
