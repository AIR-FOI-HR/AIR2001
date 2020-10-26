using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class StavkaDegustacijskogMenija
    {
        public int ProizvodIdProizvod { get; set; }
        public int DegustacijskiMeniIdDegustacijskiMeni { get; set; }
        public int Kolicina { get; set; }

        public virtual DegustacijskiMeni DegustacijskiMeniIdDegustacijskiMeniNavigation { get; set; }
        public virtual Proizvod ProizvodIdProizvodNavigation { get; set; }
    }
}
