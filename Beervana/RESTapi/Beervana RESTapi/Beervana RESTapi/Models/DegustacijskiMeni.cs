using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class DegustacijskiMeni
    {
        public DegustacijskiMeni()
        {
            StavkaDegustacijskogMenija = new HashSet<StavkaDegustacijskogMenija>();
        }

        public int IdDegustacijskiMeni { get; set; }
        public int IdLokacija { get; set; }
        public string NazivMenija { get; set; }
        public string SlikaMenija { get; set; }
        public string OpisMenija { get; set; }
        public DateTime DatumKreiranja { get; set; }
        public int Trajanje { get; set; }
        public int IdKorisnik { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual Lokacija IdLokacijaNavigation { get; set; }
        public virtual ICollection<StavkaDegustacijskogMenija> StavkaDegustacijskogMenija { get; set; }
    }
}
