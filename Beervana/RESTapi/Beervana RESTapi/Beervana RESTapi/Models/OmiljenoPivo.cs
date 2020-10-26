using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class OmiljenoPivo
    {
        public int ProizvodIdProizvod { get; set; }
        public int KorisnikIdKorisnik { get; set; }
        public string DatumDodavanja { get; set; }

        public virtual Korisnik KorisnikIdKorisnikNavigation { get; set; }
        public virtual Proizvod ProizvodIdProizvodNavigation { get; set; }
    }
}
