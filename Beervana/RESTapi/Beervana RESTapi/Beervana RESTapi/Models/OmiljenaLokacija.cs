using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class OmiljenaLokacija
    {
        public int LokacijaIdLokacija { get; set; }
        public int KorisnikIdKorisnik { get; set; }
        public DateTime DatumDodavanja { get; set; }

        public virtual Korisnik KorisnikIdKorisnikNavigation { get; set; }
        public virtual Lokacija LokacijaIdLokacijaNavigation { get; set; }
    }
}
